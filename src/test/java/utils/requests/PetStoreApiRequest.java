package utils.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;

public interface PetStoreApiRequest {

    Response getResponse();

    RequestSpecification getRequestSpec();

    void setRequestData(RequestData requestData);

    static PetStoreApiRequest from(String path, HttpMethods httpMethods) {
        return ApiRequestFactory.getRequest(path, httpMethods);
    }

    static PetStoreApiRequest from(String path, String httpMethod) {
        return from(path, HttpMethods.from(httpMethod));
    }
}
