# Created by marcm at 01/08/2024
# Edited by alastair-f-smith on 09/08/2024
Feature: Create user on API
  As a user I want to be able to create a new account so that i can perform other operations on the API.

  Scenario: Create valid user
    Given I have the following user details
      | id          | 1                   |
      | username    | example1            |
      | email       | test1@example38.com |
      | password    | password            |
      | firstName   | Test                |
      | lastName    | Example             |
      | phoneNumber | 04823748928         |
      | userStatus  | 1                   |
    When I perform a POST request
    Then A 200 status code is returned

  Scenario: Create user with existing id
    Given I have the following user details
      | id          | 1                   |
      | username    | example2            |
      | email       | test2@example38.com |
      | password    | password            |
      | firstName   | Test                |
      | lastName    | Example             |
      | phoneNumber | 04823748928         |
      | userStatus  | 1                   |
    When I perform a POST request
    Then A 200 status code is returned

  Scenario Outline: Create user with existing username
    Given I have the following user details
      | id          | 2                 |
      | username    | <username>        |
      | email       | test3@example.com |
      | password    | password          |
      | firstName   | Test              |
      | lastName    | Example           |
      | phoneNumber | 04823748928       |
      | userStatus  | 1                 |
    When I perform a POST request
    Then A 200 status code is returned
    Examples:
      | username |
      | example1 |
      | example2 |