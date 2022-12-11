@tag
Feature: Unlimint Test Cases

  @tag
  Scenario: Get the Sender and Recipient Data
    Given Hit the genrate user API
    When Navigate to site of the test store
    Then Register new user on the test store site using the data of user API
    Then Add any item to cart
    Then Proceed to checkout
    Then Verify the purchase
