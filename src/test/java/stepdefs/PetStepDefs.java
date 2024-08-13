package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import pojos.Category;
import pojos.Pet;
import utils.requestdata.RequestData;
import utils.requestspecs.PetRequestSpecs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

public class PetStepDefs extends AbstractAPI {

    private static Pet pet;
    private List<Pet> pets;
    private String providedStatus;

    @Given("I have the following valid pet data:")
    public void iHaveTheFollowingValidPetData(DataTable dataTable) {
        Map<String, String> petData = dataTable.asMap();
        pet = Pet.fromDataTableRow(petData);
        setPetDataInBody();
    }

    private void setPetDataInBody() {
        setRequestData(RequestData.petData()
                                  .body(pet)
                                  .build());
    }

    @Given("I include the following valid category data:")
    public void iIncludeTheFollowingValidCategoryData(DataTable dataTable) {
        Map<String, String> categoryData = dataTable.asMap();
        pet.setCategory(Category.fromDataTableRow(categoryData));
        setPetDataInBody();
    }

    @Given("I have prepared a URL with {string}")
    public void iHavePreparedAURLWith(String status) {
        providedStatus = status;
        setRequestData(RequestData.petData()
                                  .status(status)
                                  .build());
    }

    @Given("I have prepared a URL without a status parameter")
    public void iHavePreparedAURLWithoutAStatusParameter() {
        setRequestData(RequestData.petData()
                                  .build());
    }

    @Then("the response body contains pet data that matches the data I sent")
    public void theResponseBodyContainsPetDataThatMatchesTheDataISent() {
        MatcherAssert.assertThat(getResponse().as(Pet.class), is(pet));
    }

    @Then("The response body contains more than one pet")
    public void theResponseBodyContainsMoreThanOnePet() {
        fetchPetsIfAbsent();
        MatcherAssert.assertThat(pets.size(), greaterThanOrEqualTo(1));
    }

    private void fetchPetsIfAbsent() {
        if (pets == null) {
            pets = Arrays.asList(getResponse().getBody().as(Pet[].class));
        }
    }

    @Then("The returned pets have the requested status")
    public void theReturnedPetsHaveTheRequestedStatus() {
        fetchPetsIfAbsent();
        MatcherAssert.assertThat(pets.getFirst().getStatus(), is(providedStatus));
    }

    @AfterAll
    public static void afterAll() {
        if (pet != null) {
            Response deletionResponse = RestAssured.given(PetRequestSpecs.deletePetRequestSpec(pet.getId()))
                                                   .delete()
                                                   .thenReturn();

            Assert.assertEquals(deletionResponse.statusCode(), 200);
        }
    }
}
