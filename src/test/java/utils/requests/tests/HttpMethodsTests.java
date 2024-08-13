package utils.requests.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.requests.HttpMethods;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HttpMethodsTests {

    @ParameterizedTest
    @MethodSource("getValidInput")
    @DisplayName("The factory method correctly converts string input to enum values in a case-insensitive manner")
    void factoryMethodValidInput(String provided, HttpMethods expected) {
        HttpMethods actual = HttpMethods.from(provided);
        assertThat(actual, is(expected));
    }

    static Stream<Arguments> getValidInput() {
        return Stream.of(
                Arguments.of("GET", HttpMethods.GET),
                Arguments.of("POST", HttpMethods.POST),
                Arguments.of("pOsT", HttpMethods.POST),
                Arguments.of("get", HttpMethods.GET)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "pst", "TEST"})
    @DisplayName("The factory method throws an IllegalArgumentException when the provided string cannot be converted to an HTTP method")
    void factoryMethodInvalidInput(String provided) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpMethods.from(provided));
    }
}
