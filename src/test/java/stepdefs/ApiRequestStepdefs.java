package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
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
}
