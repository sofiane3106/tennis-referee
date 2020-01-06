Feature: Tennis game referee
  Manage a tennis set game with score rule 2

  Scenario: Manage the DEUCE rule game
    Given players has score equals "40"
    When firstPlayer marks a point
    Then firstPlayer score should be "ADV"

  Scenario: Manage the DEUCE rule game
    Given firstPlayer has score equals "40"
    And secondPlayer has score equals "ADV"
    When firstPlayer marks a point
    Then the firstPlayer score should be "DEUCE"
    And the secondPlayer score should be "DEUCE"

  Scenario: Manage the DEUCE rule game
    Given firstPlayer has score equals "DEUCE"
    And secondPlayer has score equals "DEUCE"
    When firstPlayer marks a point
    Then the firstPlayer score should be "ADV"
    And the secondPlayer score should be "40"

  Scenario: Manage the DEUCE rule game
    Given firstPlayer has score equals "ADV"
    And secondPlayer has score equals "40"
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
