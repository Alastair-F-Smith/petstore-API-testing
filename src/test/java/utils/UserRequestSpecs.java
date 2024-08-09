package utils;

import static constants.Constants.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.User;

import java.util.HashMap;
import java.util.Map;

public class UserRequestSpecs {

	public static RequestSpecification createUser(User user) {
		var requestSpec = new RequestSpecBuilder().setBaseUri(BASE_PATH)
												  .setBasePath(USER_PATH)
												  .addHeaders(HEADERS)
												  .setContentType(ContentType.JSON)
												  .setBody(user)
												  .build();
		return RestAssured.given(requestSpec);
	}

	public static RequestSpecification getUser(String username) {
		var requestSpec = new RequestSpecBuilder().setBaseUri(BASE_PATH)
												  .setBasePath(SINGLE_USER_PATH)
												  .addHeaders(HEADERS)
												  .addPathParams(getUsernamePathParam(username))
												  .build();
		return RestAssured.given(requestSpec);
	}

	// Constructing the param map using Hashmap allows null values to be provided
	private static Map<String, String> getUsernamePathParam(String username) {
		Map<String, String> params = new HashMap<>();
		params.put("username", username);
		return params;
	}

	public static RequestSpecification login(String username, String password) {
		var requestSpec = new RequestSpecBuilder().setBaseUri(BASE_PATH)
												  .setBasePath(USER_LOGIN_PATH)
												  .addHeaders(HEADERS)
												  .addQueryParams(Map.of("username", username, "password", password))
												  .build();
		return RestAssured.given(requestSpec);
	}

	public static RequestSpecification logout() {
		var requestSpec = new RequestSpecBuilder().setBaseUri(BASE_PATH)
												  .setBasePath(USER_LOGOUT_PATH)
												  .addHeaders(HEADERS)
												  .build();
		return RestAssured.given(requestSpec);
	}


}
