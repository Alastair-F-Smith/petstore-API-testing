package utils.requests;

import constants.Constants;

import java.util.Arrays;

public class ApiRequestFactory {

    private String path;
    private HttpMethods httpMethod;

    public ApiRequestFactory(String path, HttpMethods httpMethod) {
        this.path = path;
        this.httpMethod = httpMethod;
    }

    public static PetStoreApiRequest getRequest(String path, HttpMethods httpMethod) {
        return (new ApiRequestFactory(path, httpMethod)).getRequest();
    }

    public PetStoreApiRequest getRequest() {
        BaseEndpoint baseEndpoint = BaseEndpoint.from(path);
        return switch (baseEndpoint) {
            case PET -> getPetRequest();
            case STORE -> null;
            case USER -> getUserRequest();
        };
    }

    private enum BaseEndpoint {
        PET(Constants.PET_PATH),
        STORE(Constants.STORE_PATH),
        USER(Constants.USER_PATH);

        final String path;

        BaseEndpoint(String path) {
            this.path = path;
        }

        static BaseEndpoint from(String path) {
            return Arrays.stream(BaseEndpoint.values())
                         .filter(baseEndpoint -> path.startsWith(baseEndpoint.path))
                         .findFirst()
                         .orElseThrow(() -> new IllegalArgumentException("No endpoints found matching " + path));
        }
    }

    private PetStoreApiRequest getUserRequest() {
        return UserRequest.builder()
                          .path(path)
                          .httpMethod(httpMethod)
                          .build();
    }

    private PetStoreApiRequest getPetRequest() {
        return PetRequest.builder()
                         .path(path)
                         .httpMethod(httpMethod)
                         .build();
    }
}
