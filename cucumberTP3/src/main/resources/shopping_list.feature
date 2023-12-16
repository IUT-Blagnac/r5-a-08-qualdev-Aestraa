Feature: Shopping List Verification

  Scenario: Checking the shopping list
    Given I am a user of the shopping list application
    When I add 'milk' to my list
    Then 'milk' should be displayed in my shopping list

  Scenario: Adding an item that is already present in the list
    Given 'milk' is already on my shopping list
    When I try to add 'milk' again
    Then I should see a message indicating that 'milk' is already on the list

  Scenario: Removing an item
    Given 'milk' is on my shopping list
    When I remove 'milk' from the list
    Then 'milk' should no longer be displayed in my shopping list

  Scenario Outline: Managing the shopping list
    Given I am a user of the shopping list application
    And "<item>" is on my shopping list
    When I "<action>" "<item>" to/from my list
    Then I should "<expectation>" "<item>"

    Examples:
      | item  | action   | expectation                                                  |
      | milk  | add      | see 'milk' should be displayed in my shopping list           |
      | milk  | add      | see a message indicating that 'milk' is already on the list  |
      | milk  | remove   | see 'milk' should no longer be displayed in my shopping list |