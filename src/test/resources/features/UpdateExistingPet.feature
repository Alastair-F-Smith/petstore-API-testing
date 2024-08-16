Feature: Update pet data that is currently held in the store

  As a user of the Pet Store API
  I would like to be able to update pet data once it has been added to the store
  So that I can ensure that pet data is kept up to date.

  @Happy
  Scenario: Updating pet data that is held in the store

    Given I have the following pet data:
      | id | 1 |
      | name | Test |
      | status | pending |
    And A pet with that ID is present in the store
    When I send a "PUT" request to the "/pet" endpoint
    Then A 200 status code is returned
    And the response body contains pet data that matches the data I sent

  Scenario: Updating pet data while providing only an ID

    Given I have the following pet data:
      | id | 1 |
    And A pet with that ID is present in the store
    When I send a "PUT" request to the "/pet" endpoint
    Then A 200 status code is returned
    And the response body contains pet data that matches the data I sent

  @Sad
  Scenario: Update pet data for a pet that is not present in the store

    Given I have the following pet data:
      | id | 0 |
      | name | Test |
      | status | pending |
    And A pet with that ID is not present in the store
    When I send a "PUT" request to the "/pet" endpoint
    Then A 404 status code is returned
    And The response contains the message "Pet not found"

  @Sad
  Scenario: Attempt to update pet data but do not include an ID

    Given I have the following pet data:
      | name | Test |
      | status | pending |
    When I send a "PUT" request to the "/pet" endpoint
    Then A 500 status code is returned
    And The response contains the error message "There was an error processing your request."