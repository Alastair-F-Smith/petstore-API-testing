package utils.requestdata;

import java.util.HashMap;
import java.util.Map;

public class UserData extends RequestData {

    public UserData(String username, Object body, String password) {
        super(body, new HashMap<>(), new HashMap<>(), new HashMap<>());
        getQueryParams().put("password", password);
        getQueryParams().put("username", username);
    }

    public UserData(Object body, Map<String, String> queryParams, Map<String, String> pathParams, Map<String, String> headers) {
        super(body, queryParams, pathParams, headers);
    }

    public String getPassword() {
        return getQueryParams().getOrDefault("password", null);
    }

    public String getId() {
        if (getPathParams().containsKey("username")) {
            return getPathParams().get("username");
        } else {
            return getQueryParams().get("username");
        }
    }

    public static UserDataBuilder builder() {
        return new UserDataBuilder();
    }

    public static class UserDataBuilder extends RequestDataBuilder {

        public UserDataBuilder username(String username) {
            pathParam("username", username);
            return this;
        }

        public UserDataBuilder usernameQueryParam(String username) {
            queryParam("username", username);
            return this;
        }

        public UserDataBuilder password(String password) {
            queryParam("password", password);
            return this;
        }

        public UserData build() {
            return new UserData(body, queryParams, pathParams, headers);
        }
    }
}
