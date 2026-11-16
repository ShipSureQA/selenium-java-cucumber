@checkout
Feature: Checkout
  As a shopper
  I want to buy the products in my cart
  So that I receive my order

  Background:
    Given I am logged in as "standard_user"

  @smoke @e2e
  Scenario: Complete a purchase end to end
    When I add the following products to the cart:
      | Sauce Labs Backpack      |
      | Sauce Labs Fleece Jacket |
    And I open the cart
    Then the cart should contain:
      | Sauce Labs Backpack      |
      | Sauce Labs Fleece Jacket |
    When I proceed to checkout
    And I enter first name "Test", last name "Customer" and postal code "SW1A 1AA"
    Then the order total should equal the item total plus tax
    When I finish the order
    Then I should see the confirmation "Thank you for your order!"

  Scenario Outline: Checkout form validation - <case>
    When I add "Sauce Labs Onesie" to the cart
    And I open the cart
    And I proceed to checkout
    And I enter first name "<first>", last name "<last>" and postal code "<postal>"
    Then I should see the checkout error "<error>"

    Examples:
      | case               | first | last     | postal   | error                   |
      | missing first name |       | Customer | SW1A 1AA | First Name is required  |
      | missing last name  | Test  |          | SW1A 1AA | Last Name is required   |
      | missing postcode   | Test  | Customer |          | Postal Code is required |
