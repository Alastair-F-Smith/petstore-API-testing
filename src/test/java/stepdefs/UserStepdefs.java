package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.hamcrest.MatcherAssert;
import pojos.User;
import utils.UserData;
import utils.UserRequestSpecs;

import java.util.Map;

import static org.hamcrest.Matchers.*;

public class UserStepdefs extends AbstractAPI {

    private User sentUser;
    private String username;
    private String password;

    @Given("I have the following user details")
    public void iHavePreparedARequestWith(DataTable dataTable) {
        Map<String, String> userDetails = dataTable.asMap();
        sentUser = User.from(userDetails);
        setRequestData(new UserData(sentUser.getUsername(), sentUser, sentUser.getPassword()));
    }

    @Given("I have the username {string}")
    public void iHavePreparedARequestToGetUserDetailsWith(String username) {
        setRequestData(new UserData(username, sentUser, password));
    }

    @Given("I have login details with username {string} and password {string}")
    public void iHavePreparedARequestToLoginWithUsernameAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
        setRequestData(new UserData(username, sentUser, password));
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
        username = "user1";
        password = "XXXXXXXXXXX";
        UserRequestSpecs.login(username, password)
                        .get();
        setRequestData(new UserData(username, sentUser, password));
    }

    @Given("I am not logged in")
    public void iAmNotLoggedIn() {
    }

    @When("I send a request to the logout endpoint")
    public void iSendARequestToTheLogoutEndpoint() {
        setResponse(getRequestSpecification()
                            .get()
                            .thenReturn());
    }

    @And("the user details match those expected")
    public void userDetailsMatch() {
        User receivedUser = getResponse().getBody().as(User.class);
        MatcherAssert.assertThat(receivedUser, is(sentUser));
    }

    @And("the response contains a user session ID")
    public void theResponseContainsAUserSessionID() {
        String bodyText = getResponse().getBody().asString();
        long sessionId = parseSessionId(bodyText);
        MatcherAssert.assertThat(bodyText, containsString("Logged in user session:"));
        MatcherAssert.assertThat(sessionId, greaterThanOrEqualTo(0L));
    }

    private long parseSessionId(String bodyText) {
        long sessionId;
        try {
            sessionId = Long.parseLong(bodyText.split(":")[1].replace(" ", ""));
        } catch (NumberFormatException e) {
            sessionId = -1;
        }
        return sessionId;
    }

    @But("a GET request for the username returns a {int} status code")
    public void aGETRequestForTheUsernameReturnsAStatusCode(int statusCode) {
        UserRequestSpecs.getUser(username)
                        .when()
                        .get()
                        .then()
                        .statusCode(statusCode);
    }

    @And("the response contains the message {string}")
    public void theResponseContainsTheMessage(String expectedMessage) {
        String bodyText = getResponse().getBody().asString();
        MatcherAssert.assertThat(bodyText, containsString(expectedMessage));
    }
}
