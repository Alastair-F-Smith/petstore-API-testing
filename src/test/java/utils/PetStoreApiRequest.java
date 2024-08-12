package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface PetStoreApiRequest {

    Response getResponse();

    RequestSpecification getRequestSpec();
}
