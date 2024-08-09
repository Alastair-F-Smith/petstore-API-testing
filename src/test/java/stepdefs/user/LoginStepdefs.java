package stepdefs.user;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import stepdefs.AbstractAPI;
import utils.UserUtil;

import static org.hamcrest.Matchers.*;

public class LoginStepdefs extends AbstractAPI {

    private String username;

	@Given("I have prepared a request to login with username {string} and password {string}")
	public void iHavePreparedARequestToLoginWithUsernameAndPassword(String username, String password) {
        this.username = username;
		setRequestSpecification(RestAssured.given(UserUtil.login(username, password)));
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
        RestAssured.given(UserUtil.getUser(username))
                   .when()
                   .get()
                   .then()
                   .statusCode(statusCode);
    }
}
