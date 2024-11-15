Feature: Testing Login Form on SauceDemo Website

  Scenario: UC-1 Login attempt with empty credentials
    Given I am on the SauceDemo login page
    When I enter login "User123" and password "passwordUser123"
    And I clear the username and password fields
    And I click the login button
    Then I should see an error message containing "Username is required"

  Scenario: UC-2 Login attempt with missing password
    Given I am on the SauceDemo login page
    When I enter login "User001" and password "admin"
    And I clear the password field
    And I click the login button
    Then I should see an error message containing "Password is required"

  Scenario: UC-3 Test Login form with credentials by passing Username & Password
    Given I am on the SauceDemo login page
    When I enter login "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should see validation the title "Swag Labs" in the dashboard