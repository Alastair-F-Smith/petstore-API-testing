package utils.requests;

import constants.Constants;

import java.util.Arrays;

public class RequestDispatcherFactory {

    private String path;
    private HttpMethods httpMethod;

    public RequestDispatcherFactory(String path, HttpMethods httpMethod) {
        this.path = path;
        this.httpMethod = httpMethod;
    }

    public static ApiRequestDispatcher getRequest(String path, HttpMethods httpMethod) {
        return (new RequestDispatcherFactory(path, httpMethod)).getRequest();
    }

    public ApiRequestDispatcher getRequest() {
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

    private ApiRequestDispatcher getUserRequest() {
        return UserRequestDispatcher.builder()
                                    .path(path)
                                    .httpMethod(httpMethod)
                                    .build();
    }

    private ApiRequestDispatcher getPetRequest() {
        return PetRequestDispatcher.builder()
                                   .path(path)
                                   .httpMethod(httpMethod)
                                   .build();
    }
}
