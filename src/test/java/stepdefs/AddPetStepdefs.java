package stepdefs;

import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import pojos.Pet;
import utils.PetUtils;

import static org.hamcrest.Matchers.*;

public class AddPetStepdefs extends AbstractAPI {
    private static Pet pet;

    @Given("I have constructed a request for the add pet endpoint with valid pet data")
    public void iHaveConstructedAValidRequestForTheAddPetEndpoint() {
        pet = PetUtils.getValidPet();
        setRequestSpecification(RestAssured.given(PetUtils.addPetRequestSpec(pet)));
    }

    @When("I make a POST request to the pet store API")
    public void iMakeAPOSTRequestToThePetStoreAPI() {
        setResponse(getRequestSpecification().when()
                .post()
                .thenReturn());
    }

    @Then("I receive a response with a {int} status code")
    public void iReceiveAResponseWithAStatusCode(int expected) {
        MatcherAssert.assertThat(getResponse().statusCode(), is(expected));
    }

    @And("the response body contains pet data that matches the data I sent")
    public void theResponseBodyContainsPetDataThatMatchesTheDataISent() {
        MatcherAssert.assertThat(getResponse().as(Pet.class), is(pet));
    }

    @AfterAll
    public static void afterAll() {
        if (pet != null) {
            Response deletionResponse = RestAssured.given(PetUtils.deletePetRequestSpec(pet.getId()))
                                                   .delete()
                                                   .thenReturn();

            Assert.assertEquals(deletionResponse.statusCode(), 200);
        }
    }
}
