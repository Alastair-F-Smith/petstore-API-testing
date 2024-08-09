package utils;

import static constants.Constants.*;

import io.cucumber.java.hu.Ha;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.User;

import java.util.HashMap;
import java.util.Map;

public class UserUtil {

	public static RequestSpecification createUser(User user) {
		return new RequestSpecBuilder().setBaseUri(BASE_PATH)
									   .setBasePath(USER_PATH)
									   .addHeaders(HEADERS)
									   .setContentType(ContentType.JSON)
									   .setBody(user)
									   .build();
	}

	public static RequestSpecification createBulkUser(User[] users) {
		return new RequestSpecBuilder().setBaseUri(BASE_PATH)
									   .setBasePath(USER_PATH)
									   .addHeaders(HEADERS)
									   .setContentType(ContentType.JSON)
									   .setBody(users)
									   .build();
	}

	public static RequestSpecification getUser(String username) {
		return new RequestSpecBuilder().setBaseUri(BASE_PATH)
									   .setBasePath(SINGLE_USER_PATH)
									   .addHeaders(HEADERS)
									   .addPathParams(getUsernamePathParam(username))
									   .build();
	}

	// Constructing the param map using Hashmap allows null values to be provided
	private static Map<String, String> getUsernamePathParam(String username) {
		Map<String, String> params = new HashMap<>();
		params.put("username", username);
		return params;
	}

	public static RequestSpecification updateUser(User user) {
		return new RequestSpecBuilder().setBaseUri(BASE_PATH)
									   .setBasePath(SINGLE_USER_PATH)
									   .addHeaders(HEADERS)
									   .setContentType(ContentType.JSON)
									   .setBody(user)
									   .addPathParams(getUsernamePathParam(user.getUsername()))
									   .build();
	}

	public static RequestSpecification deleteUser(String username) {
		return new RequestSpecBuilder().setBaseUri(BASE_PATH)
									   .setBasePath(SINGLE_USER_PATH)
									   .addHeaders(HEADERS)
									   .addPathParams(getUsernamePathParam(username))
									   .build();
	}

	public static RequestSpecification login(String username, String password) {
		return new RequestSpecBuilder().setBaseUri(BASE_PATH)
									   .setBasePath(USER_LOGIN_PATH)
									   .addHeaders(HEADERS)
									   .addQueryParams(Map.of("username", username, "password", password))
									   .build();

	}

	public static RequestSpecification logout() {
		return new RequestSpecBuilder().setBaseUri(BASE_PATH)
									   .setBasePath(USER_LOGOUT_PATH)
									   .addHeaders(HEADERS)
									   .build();
	}


}
