# Created by marcm at 01/08/2024
Feature: retrieve a user's details from the API
  As a user I want to be able to request a users details from the API.

  Background:
    Given I have prepared a request with the following user details
      | id          | 4                 |
      | username    | example4          |
      | email       | test4@example.com |
      | password    | password          |
      | firstName   | Test              |
      | lastName    | Example           |
      | phoneNumber | 04823748928       |
      | userStatus  | 1                 |
    When I perform a POST request

  Scenario: Get a valid existing User
    Given I have prepared a request to get user details with username "example4"
    When I perform a GET request
    Then A 200 status code is returned
    And username is "example4"
    And id is 4
    And firstName is "Test"
    And lastName is "Example"

  Scenario: Get a invalid username
    Given I have prepared a request to get user details with username null
    When I perform a GET request
    Then A 404 status code is returned

  Scenario: Get a username that doesnt exist
    Given I have prepared a request to get user details with username "example5"
    When I perform a GET request
    Then A 404 status code is returned



