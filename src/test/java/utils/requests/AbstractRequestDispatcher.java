package utils.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;

public abstract class AbstractRequestDispatcher implements ApiRequestDispatcher {

    private String path;
    private HttpMethods httpMethod;
    private RequestData requestData;

    public AbstractRequestDispatcher(String path, HttpMethods httpMethod, RequestData requestData) {
        this.path = path;
        this.httpMethod = httpMethod;
        this.requestData = requestData;
    }

    @Override
    public Response getResponse() {
        return switch(httpMethod) {
            case GET -> getRequestSpec().get();
            case POST -> getRequestSpec().post();
            case PUT -> getRequestSpec().put();
            case DELETE -> getRequestSpec().delete();
        };
    }

    @Override
    public abstract RequestSpecification getRequestSpec();

    @Override
    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethods getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethods httpMethod) {
        this.httpMethod = httpMethod;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public Object getBody() {
        return requestData.getBody();
    }

    public static abstract class ApiRequestBuilder {

        protected String path;
        protected HttpMethods httpMethod;
        protected RequestData requestData;

        public ApiRequestBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ApiRequestBuilder httpMethod(HttpMethods httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public ApiRequestBuilder requestData(RequestData requestData) {
            this.requestData = requestData;
            return this;
        }

        public abstract AbstractRequestDispatcher build();

    }
}
