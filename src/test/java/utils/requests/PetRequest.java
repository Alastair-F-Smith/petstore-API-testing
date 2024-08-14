package utils.requests;

import constants.Constants;
import io.restassured.specification.RequestSpecification;
import pojos.Pet;
import utils.requestdata.PetData;
import utils.requestdata.RequestData;
import utils.requestspecs.PetRequestSpecs;

public class PetRequest extends ApiRequest {

    public PetRequest(String path, HttpMethods httpMethod, RequestData requestData) {
        super(path, httpMethod, requestData);
    }

    @Override
    public RequestSpecification getRequestSpec() {
        return switch(getPath()) {
            case Constants.PET_PATH -> PetRequestSpecs.addPetRequestSpec(getBody());
            case Constants.PET_FIND_BY_STATUS_PATH -> PetRequestSpecs.findByStatusRequestSpec(getStatus());
            default -> throw new IllegalArgumentException("Endpoint not supported");
        };
    }

    public PetData getPetData() {
        return (PetData) getRequestData();
    }

    public Pet getBody() {
        return (Pet) super.getBody();
    }

    public String getStatus() {
        return getPetData().getStatus();
    }

    public static PetRequestBuilder builder() {
        return new PetRequestBuilder();
    }

    public static class PetRequestBuilder extends ApiRequestBuilder {

        @Override
        public PetRequest build() {
            return new PetRequest(path, httpMethod, requestData);
        }

    }
}
