package stepdefs;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractAPI {
	private static RequestSpecification requestSpecification;
	private static Response response;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		AbstractAPI.response = response;
	}

	public RequestSpecification getRequestSpecification() {
		return requestSpecification;
	}

	public void setRequestSpecification(RequestSpecification requestSpecification) {
		AbstractAPI.requestSpecification = requestSpecification;
	}
}
