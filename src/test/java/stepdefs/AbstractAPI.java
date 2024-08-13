package stepdefs;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;

public abstract class AbstractAPI {
	private static RequestSpecification requestSpecification;
	private static Response response;
	private static RequestData requestData;

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

	public static RequestData getRequestData() {
		return requestData;
	}

	public static void setRequestData(RequestData requestData) {
		AbstractAPI.requestData = requestData;
	}
}
