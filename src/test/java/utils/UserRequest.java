package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.User;
import constants.Constants;


public class UserRequest implements PetStoreApiRequest {

    private String path;
    private HttpMethods httpMethod;
    private String username;
    private String password;
    private User body;

    private UserRequest(String path, HttpMethods httpMethod, String username, String password, User body) {
        this.path = path;
        this.httpMethod = httpMethod;
        this.username = username;
        this.password = password;
        this.body = body;
    }

    @Override
    public Response getResponse() {
        if (httpMethod == HttpMethods.GET) {
            return getRequestSpec().get();
        } else {
            return getRequestSpec().post();
        }
    }

    @Override
    public RequestSpecification getRequestSpec() {
        return switch (path) {
            case Constants.USER_LOGOUT_PATH -> UserRequestSpecs.logout();
            case Constants.USER_LOGIN_PATH -> UserRequestSpecs.login(username, password);
            case Constants.USER_PATH -> UserRequestSpecs.createUser(body);
            case Constants.SINGLE_USER_PATH -> singleUserEndpoints();
            default -> throw new IllegalArgumentException("Endpoint not supported");
        };
    }

    @Override
    public void setRequestData(RequestData requestData) {
        if (requestData instanceof UserData userData) {
            username = userData.getId();
            password = userData.getPassword();
            body = (User) userData.getBody();
        } else {
            throw new IllegalArgumentException("Expected user data but got: " + requestData);
        }
    }

    private RequestSpecification singleUserEndpoints() {
        return switch(httpMethod) {
            case GET -> UserRequestSpecs.getUser(username);
            default -> throw new IllegalArgumentException("Endpoint not supported");
        };
    }

    public static UserRequestBuilder builder() {
        return new UserRequestBuilder();
    }

    public static class UserRequestBuilder {
        private String path;
        private HttpMethods httpMethod;
        private String username;
        private String password;
        private User body;

        private UserRequestBuilder() {}

        public UserRequestBuilder path(String path) {
            this.path = path;
            return this;
        }

        public UserRequestBuilder httpMethod(HttpMethods httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public UserRequestBuilder httpMethod(String httpMethod) {
            this.httpMethod = HttpMethods.from(httpMethod);
            return this;
        }

        public UserRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRequestBuilder body(User body) {
            this.body = body;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(path, httpMethod, username, password, body);
        }
    }


}
