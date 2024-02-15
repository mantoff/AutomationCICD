@tag
Feature: Purchase the order from  Ecommerce website

  Background:
    Given I landed on Ecommerce page

@Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then Confirmation message "Thankyou for the order." is displayed
    Examples:
      | username       | password | productName |  |
      | mantov@abc.com | Pa$$1234 | ZARA COAT 3 |  |