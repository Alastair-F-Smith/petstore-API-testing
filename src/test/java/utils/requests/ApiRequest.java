package utils.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;

public abstract class ApiRequest implements PetStoreApiRequest {

    private String path;
    private HttpMethods httpMethod;
    private RequestData requestData;

    public ApiRequest(String path, HttpMethods httpMethod, RequestData requestData) {
        this.path = path;
        this.httpMethod = httpMethod;
        this.requestData = requestData;
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

        public abstract ApiRequest build();

    }
}
