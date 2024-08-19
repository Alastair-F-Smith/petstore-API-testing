# Created by marcm at 02/08/2024
# Edited by alastair-f-smith on 09/08/2024
Feature: Logout
  As a logged-in user I want to be able to log out
  so that I can end my session.

  Scenario: Logging out while logged in
    Given I am logged in
    When I send a "GET" request to the "/user/logout" endpoint
    Then A 200 status code is returned
    And The response contains the message "User logged out"

  Scenario: Logging out while not logged in
    Given I am not logged in
    When I send a "GET" request to the "/user/logout" endpoint
    Then A 200 status code is returned
    And The response contains the message "User logged out"