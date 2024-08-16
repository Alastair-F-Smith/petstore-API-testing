package utils.requests;

import constants.Constants;
import io.restassured.specification.RequestSpecification;
import pojos.Pet;
import pojos.UpdatePetForm;
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
            case Constants.SINGLE_PET_PATH -> handleSinglePetRequest();
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

    public RequestSpecification handleSinglePetRequest() {
        if (getHttpMethod() == HttpMethods.POST) {
            return PetRequestSpecs.updatePetFormRequestSpec(getRequestData().getId(), getForm());
        } else {
            return PetRequestSpecs.singlePetRequestSpec(getRequestData().getId());
        }
    }

    private UpdatePetForm getForm() {
        return (UpdatePetForm) super.getBody();
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
