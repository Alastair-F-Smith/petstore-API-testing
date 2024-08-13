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
        PetData petData = getPetData();
        RequestSpecification requestSpecification =  switch(getPath()) {
            case Constants.PET_PATH -> PetRequestSpecs.addPetRequestSpec((Pet) petData.getBody());
            case Constants.PET_FIND_BY_STATUS_PATH -> PetRequestSpecs.findByStatusRequestSpec(petData.getStatus());
            default -> throw new IllegalArgumentException("Endpoint not supported");
        };
        System.out.println(requestSpecification);
        return requestSpecification;
    }

    public PetData getPetData() {
        return (PetData) getRequestData();
    }
}
