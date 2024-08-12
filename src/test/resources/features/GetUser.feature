# Created by marcm at 01/08/2024
# Edited by alastair-f-smith on 09/08/2024
Feature: retrieve a user's details from the API
  As a user I want to be able to request a users details from the API.

  Background:
    Given I have the following user details
      | id          | 4                 |
      | username    | example4          |
      | email       | test4@example.com |
      | password    | password          |
      | firstName   | Test              |
      | lastName    | Example           |
      | phoneNumber | 04823748928       |
      | userStatus  | 1                 |
    When I send a "POST" request to the "/user" endpoint

  Scenario: Get a valid existing User
    Given I have the username "example4"
    When I send a "GET" request to the "/user/{username}" endpoint
    Then A 200 status code is returned
    And the user details match those expected

  Scenario Outline: Get a username that is not present or invalid
    Given I have the username "<username>"
    When I send a "GET" request to the "/user/{username}" endpoint
    Then A 404 status code is returned
    And the response contains the message "User not found"
    Examples:
      | username |
      | null     |
      | example5 |