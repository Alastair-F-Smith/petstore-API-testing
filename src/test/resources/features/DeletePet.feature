Feature: Delete a pet from the store

  As a user of the store
  I want to be able to delete the details of a specified pet from the store
  So that I can remove pet details when they are no longer relevant or needed

  Scenario Outline: Delete a pet using a valid ID for a pet that is present in the store
    Given I have the pet ID "<petId>"
    And A pet with that ID is present in the store
    When I send a "DELETE" request to the "/pet/{petId}" endpoint
    Then A 200 status code is returned
    And The response contains the message "Pet deleted"
    And The pet has been removed from the store
    Examples:
      | petId |
      | 11    |
      | 20    |

  Scenario Outline: Delete a pet using a valid ID but which is not present in the store
    Given I have the pet ID "<petId>"
    When I send a "DELETE" request to the "/pet/{petId}" endpoint
    Then A 200 status code is returned
    And The response contains the message "Pet deleted"
    Examples:
      | petId |
      | 11    |
      | 20    |

  Scenario Outline: Delete a pet using an invalid ID
    Given I have the pet ID "<petId>"
    When I send a "DELETE" request to the "/pet/{petId}" endpoint
    Then A 400 status code is returned
    And The response contains the error message "Input error:"
    Examples:
      | petId   |
      | []      |
      | null    |