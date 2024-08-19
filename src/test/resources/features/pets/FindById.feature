Feature: Find a pet by its ID

  As an API I user,
  I want to be able to find data about a pet using its ID
  so that I can find information about a specific pet

  Scenario Outline: Find pets using IDs that are valid and exist in the store
    Given I have the pet ID "<petId>"
    When I send a "GET" request to the "/pet/{petId}" endpoint
    Then A 200 status code is returned
    And The response contains pet data with the pet Id "<petId>"
    Examples:
      | petId |
      | 1     |
      | 2     |
      | 10    |

  Scenario Outline: Find pets using IDs that are valid but are not in the store
    Given I have the pet ID "<petId>"
    When I send a "GET" request to the "/pet/{petId}" endpoint
    Then A 404 status code is returned
    And The response contains the message "Pet not found"
    Examples:
      | petId |
      | 111   |
      | 20    |
      | 0     |
      | -1    |

  Scenario Outline: Find pets using invalid IDs
    Given I have the pet ID "<petId>"
    When I send a "GET" request to the "/pet/{petId}" endpoint
    Then A 400 status code is returned
    And The response contains the error message "Input error:"
    Examples:
      | petId |
      | a     |
      | []    |
      | null  |