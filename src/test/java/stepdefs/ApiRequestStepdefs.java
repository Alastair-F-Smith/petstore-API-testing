package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import utils.PetStoreApiRequest;
import utils.UserRequest;

import static org.hamcrest.Matchers.is;

public class ApiRequestStepdefs extends AbstractAPI {
	@Then("A {int} status code is returned")
	public void aStatusCodeIsReturned(int expectedStatusCode) {
		MatcherAssert.assertThat(getResponse().statusCode(),is(expectedStatusCode));
	}

	@When("I perform a POST request")
	public void iPerformAPOSTRequest() {
		setResponse(getRequestSpecification().post().thenReturn());
	}

	@When("I perform a GET request")
	public void iPerformGETRequest() {
		setResponse(getRequestSpecification().get().thenReturn());
	}

	@When("I send a {string} request to the {string} endpoint")
	public void iSendARequestToTheEndpoint(String httpMethod, String path) {
		PetStoreApiRequest request = PetStoreApiRequest.from(path, httpMethod);
		request.setRequestData(getRequestData());
		setResponse(request.getResponse());
	}
}
