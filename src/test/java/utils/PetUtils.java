package utils;

import constants.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import pojos.Pet;

public class PetUtils {

    public static RequestSpecification findByStatusRequestSpec(String status) {
        return getRequestSpecBuilderWithPath(Constants.PET_FIND_BY_STATUS_PATH)
                .addQueryParam("status", status)
                .build();
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
        return getRequestSpecBuilderWithPath(Constants.PET_PATH)
                .setBody(pet)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public static RequestSpecification deletePetRequestSpec(long id) {
        return getRequestSpecBuilderWithPath(Constants.SINGLE_PET_PATH)
                .addPathParam("petId", id)
                .build();
    }
}
