package utils.requestspecs;

import io.restassured.specification.RequestSpecification;

public class RequestSpecNonNullParams {

    private RequestSpecification requestSpec;

    public RequestSpecNonNullParams(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public RequestSpecification get() {
        return requestSpec;
    }

    public RequestSpecNonNullParams queryParam(String name, String value) {
        if (value != null) {
            requestSpec.queryParam(name, value);
        }
        return this;
    }
}
