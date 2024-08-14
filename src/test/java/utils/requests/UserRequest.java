package utils.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.User;
import constants.Constants;
import utils.requestdata.RequestData;
import utils.requestdata.UserData;
import utils.requestspecs.UserRequestSpecs;


public class UserRequest extends ApiRequest {

    private UserRequest(String path, HttpMethods httpMethod, RequestData requestData) {
        super(path, httpMethod, requestData);
    }

    @Override
    public Response getResponse() {
        if (getHttpMethod() == HttpMethods.GET) {
            return getRequestSpec().get();
        } else {
            return getRequestSpec().post();
        }
    }

    @Override
    public RequestSpecification getRequestSpec() {
        return switch (getPath()) {
            case Constants.USER_LOGOUT_PATH -> UserRequestSpecs.logout();
            case Constants.USER_LOGIN_PATH -> UserRequestSpecs.login(getUsername(), getPassword());
            case Constants.USER_PATH -> UserRequestSpecs.createUser(getUserData());
            case Constants.SINGLE_USER_PATH -> singleUserEndpoints();
            default -> throw new IllegalArgumentException("Endpoint not supported");
        };
    }

    private RequestSpecification singleUserEndpoints() {
        return switch(getHttpMethod()) {
            case GET -> UserRequestSpecs.getUser(getUsername());
            default -> throw new IllegalArgumentException("Endpoint not supported");
        };
    }

    public static UserRequestBuilder builder() {
        return new UserRequestBuilder();
    }

    public String getUsername() {
        return getRequestData().getId();
    }

    public String getPassword() {
        UserData userData = (UserData) getRequestData();
        return userData.getPassword();
    }

    public User getUserData() {
        return (User) getBody();
    }

    public static class UserRequestBuilder extends ApiRequestBuilder {

        @Override
        public UserRequest build() {
            return new UserRequest(path, httpMethod, requestData);
        }
    }


}
