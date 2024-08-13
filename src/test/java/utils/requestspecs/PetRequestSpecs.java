package utils.requestspecs;

import constants.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import pojos.Pet;

public class PetRequestSpecs {

    public static RequestSpecification findByStatusRequestSpec(String status) {
        RequestSpecification requestSpec;

        if (status == null) {
            requestSpec = findByStatusNoQueryParamRequestSpec();
        } else {
            requestSpec =  getRequestSpecBuilderWithPath(Constants.PET_FIND_BY_STATUS_PATH)
                    .addQueryParam("status", status)
                    .build();
        }
        return RestAssured.given(requestSpec);
    }

    public static RequestSpecification findByStatusNoQueryParamRequestSpec() {
        return getRequestSpecBuilderWithPath(Constants.PET_FIND_BY_STATUS_PATH)
                .build();
    }

    private static RequestSpecBuilder getRequestSpecBuilderWithPath(String path) {
        return new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_PATH)
                .setBasePath(path);
    }

    public static RequestSpecification addPetRequestSpec(Pet pet) {
        var requestSpec = getRequestSpecBuilderWithPath(Constants.PET_PATH)
                .setBody(pet)
                .addHeader("Content-Type", "application/json")
                .build();
        return RestAssured.given(requestSpec);
    }

    public static RequestSpecification deletePetRequestSpec(long id) {
        var requestSpec = getRequestSpecBuilderWithPath(Constants.SINGLE_PET_PATH)
                .addPathParam("petId", id)
                .build();
        return RestAssured.given(requestSpec);
    }
}
