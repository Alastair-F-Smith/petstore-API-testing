package utils.requests;

import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;
import utils.requestspecs.BaseRequestSpecs;

public class PetRequestDispatcher extends AbstractRequestDispatcher {

    public PetRequestDispatcher(String path, HttpMethods httpMethod, RequestData requestData) {
        super(path, httpMethod, requestData);
    }

    @Override
    public RequestSpecification getRequestSpec() {
        return BaseRequestSpecs.getRequestSpec(getPath(), getRequestData());
    }

    public static PetRequestBuilder builder() {
        return new PetRequestBuilder();
    }

    public static class PetRequestBuilder extends ApiRequestBuilder {

        @Override
        public PetRequestDispatcher build() {
            return new PetRequestDispatcher(path, httpMethod, requestData);
        }

    }
}
