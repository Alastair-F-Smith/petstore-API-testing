package utils.journeys;

import constants.Constants;
import io.restassured.response.Response;
import pojos.Pet;
import utils.requestdata.RequestData;
import utils.requests.RequestDispatcher;
import utils.requests.HttpMethods;

import java.util.Optional;

public class PetJourneys {

    public static Response findById(String id) {
        return getResponse(Constants.SINGLE_PET_PATH, HttpMethods.GET,
                           RequestData.petData()
                                      .petId(id)
                                      .build());
    }

    public static Optional<Pet> getPetById(String id) {
        Response response = findById(id);
        if (response.statusCode() == 200) {
            return Optional.of(response.as(Pet.class));
        } else {
            return Optional.empty();
        }
    }

    private static Response getResponse(String path, HttpMethods httpMethod, RequestData requestData) {
        return RequestDispatcher.builder()
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

    public static Response updatePet(Pet pet) {
        return getResponse(Constants.PET_PATH, HttpMethods.PUT,
                           RequestData.petData()
                                   .body(pet)
                                   .build());
    }
}
