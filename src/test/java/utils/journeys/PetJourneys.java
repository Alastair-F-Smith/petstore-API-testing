package utils.journeys;

import constants.Constants;
import io.restassured.response.Response;
import pojos.Pet;
import utils.requestdata.RequestData;
import utils.requests.HttpMethods;
import utils.requests.PetRequest;

public class PetJourneys {

    public static Response findById(String id) {
        return getResponse(Constants.SINGLE_PET_PATH, HttpMethods.GET,
                           RequestData.petData()
                                      .petId(id)
                                      .build());
    }

    private static Response getResponse(String path, HttpMethods httpMethod, RequestData requestData) {
        return PetRequest.builder()
                         .path(path)
                         .httpMethod(httpMethod)
                         .requestData(requestData)
                         .build()
                         .getResponse();
    }

    public static Response addPet(Pet pet) {
        return getResponse(Constants.PET_PATH, HttpMethods.POST,
                           RequestData.petData()
                                      .body(pet)
                                      .build());
    }

    public static Response addPet(String id) {
        Pet pet = new Pet();
        pet.setId(Long.parseLong(id));
        return addPet(pet);
    }

    public static Response deletePet(String id) {
        return getResponse(Constants.SINGLE_PET_PATH, HttpMethods.DELETE,
                           RequestData.petData()
                                      .petId(id)
                                      .build());
    }

    public static Response deletePet(long id) {
        return deletePet(String.valueOf(id));
    }
}
