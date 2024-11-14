Feature: User Login Attempt on SauceDemo
  Scenario: Attempt to login with invalid credentials and check for error message
    Given I am on the SauceDemo login page
    When I enter login "User123" and password "passwordUser123"
    And I clear the username and password fields
    And I click the login button
    Then I should see an error message containing "Username is required"