package utils.requests;

import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;
import utils.requestspecs.BaseRequestSpecs;

public class UserRequestDispatcher extends AbstractRequestDispatcher {

    private UserRequestDispatcher(String path, HttpMethods httpMethod, RequestData requestData) {
        super(path, httpMethod, requestData);
    }

    @Override
    public RequestSpecification getRequestSpec() {
        return BaseRequestSpecs.getRequestSpec(getPath(), getRequestData());
    }

    public static UserRequestBuilder builder() {
        return new UserRequestBuilder();
    }

    public static class UserRequestBuilder extends ApiRequestBuilder {

        @Override
        public UserRequestDispatcher build() {
            return new UserRequestDispatcher(path, httpMethod, requestData);
        }
    }


}
