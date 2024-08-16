package stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import pojos.Category;
import pojos.Pet;
import pojos.UpdatePetForm;
import utils.journeys.PetJourneys;
import utils.serialization.JacksonFilterFactory;
import utils.requestdata.RequestData;
import utils.requestspecs.PetRequestSpecs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.*;

public class PetStepDefs extends AbstractAPI {

    private static Pet pet;
    private static Pet existingPet;
    private List<Pet> pets;
    private String petId;
    private String providedStatus;
    private UpdatePetForm formData;

    @BeforeAll
    public static void setUp() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (type, s) -> {
                    FilterProvider filter = new SimpleFilterProvider()
                            .addFilter("idFilter", JacksonFilterFactory.getFilter("id",
                                                                                  pojo -> ((Pet) pojo).getId(),
                                                                                  id -> id >= 0));
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.setFilterProvider(filter);
                    return objectMapper;
                }
        ));
    }

    @Given("I have the following pet data:")
    public void iHaveTheFollowingPetData(DataTable dataTable) {
        Map<String, String> petData = dataTable.asMap();
        pet = Pet.fromDataTableRow(petData);
        petId = String.valueOf(pet.getId());
        existingPet = PetJourneys.getPetById(petId).orElse(null);
        setPetDataInBody();
    }

    @Given("I do not have any pet data")
    public void iDoNotHaveAnyPetData() {
        pet = null;
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

    @Given("I have the pet ID {string}")
    public void iHaveThePetID(String petId) {
        this.petId = petId;
        setRequestData(RequestData.petData()
                                  .petId(petId)
                                  .build());
    }

    @Given("A pet with that ID is present in the store")
    public void aPetWithThatIDIsPresentInTheStore() {
        PetJourneys.addPet(petId);
    }

    @Given("A pet with that ID is not present in the store")
    public void aPetWithThatIDIsNotPresentInTheStore() {
        Response response = PetJourneys.findById(petId);
        MatcherAssert.assertThat(response.statusCode(), is(404));
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
        MatcherAssert.assertThat(allHaveProvidedStatus(), is(true));
    }

    private boolean allHaveProvidedStatus() {
        return pets.stream()
                   .allMatch(pet -> providedStatus.equals(pet.getStatus()));
    }

    @Then("The response contains pet data with the pet Id {string}")
    public void theResponseContainsPetDataWithThePetId(String expectedId) {
        long petId = getResponse().as(Pet.class).getId();
        MatcherAssert.assertThat(petId, is(Long.parseLong(expectedId)));
    }

    @Then("The pet has been removed from the store")
    public void thePetHasBeenRemovedFromTheStore() {
        int statusCode = PetJourneys.findById(petId)
                                    .statusCode();
        MatcherAssert.assertThat(statusCode, is(404));
    }

    @AfterAll
    public static void afterAll() {
         if (existingPet != null) {
            // Restore pet data to original
            PetJourneys.updatePet(pet);
        }
    }

    @Given("I have the following form data:")
    public void iHaveTheFollowingFormData(DataTable dataTable) {
        this.formData = UpdatePetForm.from(dataTable.asMap());
        existingPet = PetJourneys.getPetById(formData.getPetId())
                                 .orElse(null);
        setRequestData(RequestData.petData()
                                  .petId(formData.getPetId())
                                  .body(formData)
                                  .build());
    }

    @Then("The response contains the updated data")
    public void theResponseContainsTheUpdatedData() {
        pet = getResponse().as(Pet.class);
        MatcherAssert.assertThat(formData.matches(pet), is(true));
    }

    @And("The status is removed")
    public void theStatusIsRemoved() {
        MatcherAssert.assertThat(pet.getStatus(), nullValue());
    }
}
