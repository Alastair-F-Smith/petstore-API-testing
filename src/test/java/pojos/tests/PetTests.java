package pojos.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pojos.Pet;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class PetTests {

    private final String validId = "1234";
    private final String validName = "test";
    private final String validStatus = "pending";

    @Nested
    @DisplayName("From data table row factory method tests")
    public class FromDataTableRowTests {
        @Test
        @DisplayName("A pet object with correct data is created when all data is provided")
        void aPetObjectWithCorrectDataWhenAllDataIsProvided() {
            Map<String, String> row = Map.of(
                    "id", validId,
                    "name", validName,
                    "status", validStatus
            );
            Pet pet = Pet.fromDataTableRow(row);

            assertThat(pet.getId(), is(1234L));
            assertThat(pet.getName(), is("test"));
            assertThat(pet.getStatus(), is("pending"));
        }

        @Test
        @DisplayName("Name is set to an empty string when no name is provided")
        void nameIsSetToAnEmptyStringWhenNoNameIsProvided() {
            Map<String, String> row = Map.of(
                    "id", validId,
                    "status", validStatus
            );
            Pet pet = Pet.fromDataTableRow(row);

            assertThat(pet.getName(), is(""));
        }

        @Test
        @DisplayName("Id is set to -1 when no ID is provided")
        void idIsSetTo1WhenNoIdIsProvided() {
            Map<String, String> row = Map.of(
                    "name", validName,
                    "status", validStatus
            );
            Pet pet = Pet.fromDataTableRow(row);

            assertThat(pet.getId(), is(-1L));
        }

        @Test
        @DisplayName("Status is set to 'available' when no status is provided")
        void statusIsSetToAvailableWhenNoStatusIsProvided() {
            Map<String, String> row = Map.of(
                    "id", validId,
                    "name", validName
            );
            Pet pet = Pet.fromDataTableRow(row);

            assertThat(pet.getStatus(), is("available"));
        }
    }


}
