Feature: Error validation

  @ErrorValidation
  Scenario Outline: Validate errors are displayed on wrong credentials and ..?
    Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "incorrect email or password." is displayed

    Examples:
      | username       | password  |
      | mantov@abc.com | dsaafsfsa |