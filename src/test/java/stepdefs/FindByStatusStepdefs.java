package stepdefs;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import pojos.Pet;
import utils.PetUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class FindByStatusStepdefs extends AbstractAPI {

    private List<Pet> pets;
    private String providedStatus;

    @Given("I have prepared a URL with {string}")
    public void iHavePreparedAURLWith(String status) {
        providedStatus = status;
        setRequestSpecification(RestAssured.given(PetUtils.findByStatusRequestSpec(status)));
    }

    @Given("I have prepared a URL without a status parameter")
    public void iHavePreparedAURLWithoutAStatusParameter() {
        setRequestSpecification(RestAssured.given(PetUtils.findByStatusNoQueryParamRequestSpec()));
    }

    @And("I retrieve the pet data from the response body")
    public void iRetrieveThePetDataFromTheResponseBody() {
        pets = Arrays.asList(getResponse().getBody().as(Pet[].class));
    }

    @And("The response body contains more than one pet")
    public void theResponseBodyContainsMoreThanOnePet() {
        MatcherAssert.assertThat(pets.size(), greaterThanOrEqualTo(1));
    }

    @And("The returned pets have the requested status")
    public void theReturnedPetsHaveTheRequestedStatus() {
        MatcherAssert.assertThat(pets.getFirst().getStatus(), is(providedStatus));
    }

    @And("The response body contains the error message {string}")
    public void theResponseBodyContainsTheErrorMessage(String expectedErrorMessage) {
        MatcherAssert.assertThat(getResponse().jsonPath().getString("message"), containsString(expectedErrorMessage));
    }

    @And("The response body contains the message {string}")
    public void theResponseBodyContainsTheMessage(String expectedMessage) {
        String body = getResponse().asString();
        MatcherAssert.assertThat(body, is(expectedMessage));
    }
}
