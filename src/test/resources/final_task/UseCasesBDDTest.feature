Feature: Testing Login Form on SauceDemo Website

  Background:
  Given I am on the SauceDemo login page

  Scenario: UC-1 Login attempt with empty credentials
    When I enter login "propertiesLogin1" and password "propertiesPassword1"
    And I clear the "login" field
    And I clear the "password" field
    And I click the login button
    Then I should see an error message containing "Username is required"

  Scenario: UC-2 Login attempt with missing password
    When I enter login "propertiesLogin2" and password "propertiesPassword2"
    And I clear the "password" field
    And I click the login button
    Then I should see an error message containing "Password is required"

  Scenario: UC-3 Test Login form with credentials by passing Username & Password
    When I enter login "propertiesLogin3" and password "propertiesPassword3"
    And I click the login button
    Then I should see validation the title "Swag Labs" in the dashboard