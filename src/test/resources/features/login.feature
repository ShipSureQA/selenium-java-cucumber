@login
Feature: Login
  As a registered customer
  I want to log in to the store
  So that I can browse and buy products

  Background:
    Given I am on the login page

  @smoke
  Scenario: Standard user logs in successfully
    When I log in as "standard_user"
    Then I should see the products page

  Scenario: Locked-out user cannot log in
    When I log in as "locked_out_user"
    Then I should see the login error "Sorry, this user has been locked out."
    And I should stay on the login page

  Scenario Outline: Login validation - <case>
    When I log in as "<username>" with password "<password>"
    Then I should see the login error "<error>"

    Examples:
      | case             | username      | password       | error                              |
      | missing username |               | secret_sauce   | Username is required               |
      | missing password | standard_user |                | Password is required               |
      | wrong password   | standard_user | wrong_password | Username and password do not match |
