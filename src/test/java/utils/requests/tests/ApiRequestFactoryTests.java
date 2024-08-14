package utils.requests.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.requests.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApiRequestFactoryTests {

    @ParameterizedTest
    @ValueSource(strings = {"/pet", "/pet/findByStatus"})
    @DisplayName("When a path in a pet endpoint is provided, a pet request is returned")
    void whenAPathInAPetEndpointIsProvidedAPetRequestIsReturned(String path) {
        PetStoreApiRequest request = ApiRequestFactory.getRequest(path, HttpMethods.GET);
        assertThat(request, instanceOf(PetRequest.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/user", "/user/login", "/user/logout"})
    @DisplayName("When a path to a user endpoint is provided, a user request is returned")
    void whenAPathToAUserEndpointIsProvidedAUserRequestIsReturned(String path) {
        PetStoreApiRequest request = ApiRequestFactory.getRequest(path, HttpMethods.GET);
        assertThat(request, instanceOf(UserRequest.class));
    }

    @ParameterizedTest
    @EnumSource(HttpMethods.class)
    @DisplayName("Created requests have the correct HTTP method")
    void createdRequestsHaveTheCorrectHttpMethod(HttpMethods httpMethod) {
        ApiRequest request = (ApiRequest) ApiRequestFactory.getRequest("/pet", httpMethod);
        assertThat(request.getHttpMethod(), is(httpMethod));
    }

    @Test
    @DisplayName("When a path that does not match any endpoints is provided, an exception is thrown")
    void whenAPathThatDoesNotMatchAnyEndpointsIsProvidedAnExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> ApiRequestFactory.getRequest("/", HttpMethods.GET)) ;
    }
}
