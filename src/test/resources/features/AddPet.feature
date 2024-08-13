Feature: Add a pet to the store

  As a user, I want to be able to add a pet to the store
  so that I can update the store with information about my pets

  Scenario: Add a valid pet

    Given I have the following valid pet data:
    | id | 123456 |
    | name | Test |
    | status | pending |
    And I include the following valid category data:
    | id | 7890 |
    | name | Cats |
    When I send a "POST" request to the "/pet" endpoint
    Then A 200 status code is returned
    And the response body contains pet data that matches the data I sent