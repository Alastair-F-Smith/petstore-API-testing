# Created by marcm at 02/08/2024
Feature: login as an existing user
  As an existing user I wish to be able to login to do more restricted API operations.
  Background:
    Given I have prepared a request with the following user details
      | id          | 6                 |
      | username    | example6          |
      | email       | test6@example.com |
      | password    | password          |
      | firstName   | Test              |
      | lastName    | Example           |
      | phoneNumber | 04823748928       |
      | userStatus  | 1                 |
    When I perform a POST request

  Scenario: Login as a valid user
    Given I have prepared a request to login with username "example6" and password "password"
    When I perform a GET request
    Then A 200 status code is returned
    And the response contains a user session ID

  Scenario: Login as a valid user with wrong password
    Given I have prepared a request to login with username "example6" and password "notMyPassword"
    When I perform a GET request
    Then A 200 status code is returned
    And the response contains a user session ID

  Scenario: Login as a user that does not exist
    Given I have prepared a request to login with username "example7" and password "password"
    When I perform a GET request
    Then A 200 status code is returned
    And the response contains a user session ID
    But a GET request for the username returns a 404 status code


