Feature: Update a pet using form data

  Scenario: Updating pet details by providing valid data

    Given I have the following form data:
      | petId  | 1         |
      | name   | updated   |
      | status | available |
    When I send a "POST" request to the "/pet/{petId}" endpoint
    Then A 200 status code is returned
    And The response contains the updated data


  Scenario: Attempting to update pet details without providing a name

    Given I have the following form data:
      | petId  | 1         |
      | status | available |
    When I send a "POST" request to the "/pet/{petId}" endpoint
    Then A 400 status code is returned
    And The response contains the message "No Name provided. Try again?"


  Scenario: Updating pet details without providing a status value

    Given I have the following form data:
      | petId  | 1         |
      | name   | updated   |
    When I send a "POST" request to the "/pet/{petId}" endpoint
    Then A 200 status code is returned
    And The response contains the updated data
    And The status is removed


  Scenario: Attempting to update pet details without providing a name or status

    Given I have the following form data:
      | petId  | 1         |
    When I send a "POST" request to the "/pet/{petId}" endpoint
    Then A 400 status code is returned
    And The response contains the message "No Name provided. Try again?"
    
    
  Scenario: Attempting to update pet details for a pet that is not in the store

    Given I have the following form data:
      | petId  | 111       |
      | name   | updated   |
      | status | available |
    When I send a "POST" request to the "/pet/{petId}" endpoint
    Then A 404 status code is returned
    And The response contains the message "Pet not found"