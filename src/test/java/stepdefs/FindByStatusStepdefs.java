package stepdefs;

import io.cucumber.java.en.*;
import org.hamcrest.MatcherAssert;
import pojos.Pet;
import utils.requestdata.RequestData;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class FindByStatusStepdefs extends AbstractAPI {

    private List<Pet> pets;
    private String providedStatus;

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

    @And("The response body contains more than one pet")
    public void theResponseBodyContainsMoreThanOnePet() {
        fetchPetsIfAbsent();
        MatcherAssert.assertThat(pets.size(), greaterThanOrEqualTo(1));
    }

    private void fetchPetsIfAbsent() {
        if (pets == null) {
            pets = Arrays.asList(getResponse().getBody().as(Pet[].class));
        }
    }

    @And("The returned pets have the requested status")
    public void theReturnedPetsHaveTheRequestedStatus() {
        fetchPetsIfAbsent();
        MatcherAssert.assertThat(pets.getFirst().getStatus(), is(providedStatus));
    }
}
