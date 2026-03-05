Feature: Purchase flow

  Scenario: Successful purchase of two products
    Given the user opens the Demoblaze homepage
    When the user adds two products to the cart
    And the user completes the purchase form
    Then the purchase should be completed successfully