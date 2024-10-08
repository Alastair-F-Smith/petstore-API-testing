# Created by marcm at 02/08/2024
# Edited by alastair-f-smith on 09/08/2024
Feature: login as an existing user
  As an existing user I wish to be able to login to do more restricted API operations.
  Background:
    Given I have the following user details
      | id          | 6                 |
      | username    | example6          |
      | email       | test6@example.com |
      | password    | password          |
      | firstName   | Test              |
      | lastName    | Example           |
      | phoneNumber | 04823748928       |
      | userStatus  | 1                 |
    When I send a "POST" request to the "/user" endpoint

  Scenario: Login as a valid user
    Given I have login details with username "example6" and password "password"
    When I send a "GET" request to the "/user/login" endpoint
    Then A 200 status code is returned
    And the response contains a user session ID

  Scenario: Login as a valid user with wrong password
    Given I have login details with username "example6" and password "notMyPassword"
    When I send a "GET" request to the "/user/login" endpoint
    Then A 200 status code is returned
    And the response contains a user session ID

  Scenario: Login as a user that does not exist
    Given I have login details with username "example7" and password "password"
    When I send a "GET" request to the "/user/login" endpoint
    Then A 200 status code is returned
    And the response contains a user session ID
    But a GET request for the username returns a 404 status code


