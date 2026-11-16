@inventory
Feature: Product inventory
  As a shopper
  I want to browse, sort and select products
  So that I can find what I want quickly

  Background:
    Given I am logged in as "standard_user"

  Scenario: All products are displayed
    Then I should see 6 products

  Scenario Outline: Sort products - <option>
    When I sort products by "<option>"
    Then product <field> should be in <direction> order

    Examples:
      | option              | field  | direction  |
      | Price (low to high) | prices | ascending  |
      | Price (high to low) | prices | descending |
      | Name (A to Z)       | names  | ascending  |
      | Name (Z to A)       | names  | descending |

  @smoke
  Scenario: Cart badge updates when adding and removing products
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    Then the cart badge should show 2
    When I remove "Sauce Labs Backpack" from the cart
    Then the cart badge should show 1
