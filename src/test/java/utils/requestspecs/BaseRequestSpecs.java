package utils.requestspecs;

import constants.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;

public class BaseRequestSpecs {

    public static RequestSpecification getRequestSpec(String path, RequestData requestData) {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_PATH)
                .setBasePath(path);

        for (var pathParam : requestData.getPathParams().entrySet()) {
            specBuilder.addPathParam(pathParam.getKey(), pathParam.getValue());
        }

        for (var queryParam : requestData.getQueryParams().entrySet()) {
            specBuilder.addQueryParam(queryParam.getKey(), queryParam.getValue());
        }

        for (var header : requestData.getHeaders().entrySet()) {
            specBuilder.addHeader(header.getKey(), header.getValue());
        }

        if (requestData.getBody() != null) {
            specBuilder.setBody(requestData.getBody());
        }

        return RestAssured.given(specBuilder.build());
    }
}
