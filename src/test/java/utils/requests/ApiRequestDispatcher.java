package utils.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;

public interface ApiRequestDispatcher {

    Response getResponse();

    RequestSpecification getRequestSpec();

    void setRequestData(RequestData requestData);

    static ApiRequestDispatcher from(String path, HttpMethods httpMethod) {
        return RequestDispatcher.builder()
                                .path(path)
                                .httpMethod(httpMethod)
                                .build();
    }

    static ApiRequestDispatcher from(String path, String httpMethod) {
        return from(path, HttpMethods.from(httpMethod));
    }
}
