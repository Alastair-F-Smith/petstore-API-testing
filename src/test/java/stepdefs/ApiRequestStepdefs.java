package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import utils.requests.ApiRequestDispatcher;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class ApiRequestStepdefs extends AbstractAPI {

	@When("I send a {string} request to the {string} endpoint")
	public void iSendARequestToTheEndpoint(String httpMethod, String path) {
		ApiRequestDispatcher request = ApiRequestDispatcher.from(path, httpMethod);
		request.setRequestData(getRequestData());
		setResponse(request.getResponse());
	}

	@Then("A {int} status code is returned")
	public void aStatusCodeIsReturned(int expectedStatusCode) {
		MatcherAssert.assertThat(getResponse().statusCode(),is(expectedStatusCode));
	}

	@Then("The response contains the message {string}")
	public void theResponseContainsTheMessage(String expectedMessage) {
		String bodyText = getResponse().getBody().asString();
		MatcherAssert.assertThat(bodyText, containsString(expectedMessage));
	}

	@Then("The response contains the error message {string}")
	public void theResponseBodyContainsTheErrorMessage(String expectedErrorMessage) {
		MatcherAssert.assertThat(getResponse().jsonPath().getString("message"), containsString(expectedErrorMessage));
	}
}
