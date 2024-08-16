package utils.requestdata;

import constants.Constants;

import java.util.HashMap;
import java.util.Map;

public abstract class RequestData {

//    private final String id;
    private final Object body;
    private final Map<String, String> queryParams;
    private final Map<String, String> pathParams;
    private final Map<String, String> headers;

    public RequestData(Object body, Map<String, String> queryParams, Map<String, String> pathParams, Map<String, String> headers) {
//        this.id = id;
        this.body = body;
        this.queryParams = queryParams;
        this.pathParams = pathParams;
        this.headers = headers;
    }

    public abstract String getId();

    public Object getBody() {
        return body;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public static UserData.UserDataBuilder userData() {
        return UserData.builder();
    }

    public static PetData.PetDataBuilder petData() {
        return PetData.builder();
    }

    public abstract static class RequestDataBuilder {

        protected Object body;
        protected Map<String, String> queryParams;
        protected Map<String, String> pathParams;
        protected Map<String, String> headers;

        protected RequestDataBuilder() {
            queryParams = new HashMap<>();
            pathParams = new HashMap<>();
            headers = new HashMap<>(Constants.HEADERS);
        }

        public RequestDataBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public RequestDataBuilder queryParam(String name, String value) {
            queryParams.put(name, value);
            return this;
        }

        public RequestDataBuilder queryParams(Map<String, String> params) {
            queryParams.putAll(params);
            return this;
        }

        public RequestDataBuilder pathParam(String name, String value) {
            pathParams.put(name, value);
            return this;
        }

        public RequestDataBuilder header(String name, String value) {
            headers.put(name, value);
            return this;
        }

        public RequestDataBuilder contentType(String type) {
            headers.put("Content-Type", type);
            return this;
        }

        public abstract RequestData build();
    }
}
