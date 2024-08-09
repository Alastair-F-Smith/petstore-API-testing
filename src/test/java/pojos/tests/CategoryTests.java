package pojos.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pojos.Category;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CategoryTests {

    private final String id = "7890";
    private final String name = "Test";

    @Nested
    @DisplayName("From data table row factory method tests")
    public class FromDataTableRowTests {
        @Test
        @DisplayName("A category object is populated with the correct data when valid data is provided")
        void aCategoryObjectIsPopulatedWithTheCorrectDataWhenValidDataIsProvided() {
            Map<String, String> row = Map.of(
                    "id", id,
                    "name", name
            );

            Category category = Category.fromDataTableRow(row);
            assertThat(category.getId(), is(7890L));
            assertThat(category.getName(), is(name));
        }

        @Test
        @DisplayName("When no id is provided, a default value of -1 is used")
        void whenNoIdIsProvidedADefaultValueOf1IsUsed() {
            Map<String, String> row = Map.of(
                    "name", name
            );

            Category category = Category.fromDataTableRow(row);

            assertThat(category.getId(), is(-1L));
        }

        @Test
        @DisplayName("When no name is provided, name is an empty string")
        void whenNoNameIsProvidedNameIsAnEmptyString() {
            Map<String, String> row = Map.of(
                    "id", id
            );

            Category category = Category.fromDataTableRow(row);

            assertThat(category.getName(), is(""));
        }
    }


}
