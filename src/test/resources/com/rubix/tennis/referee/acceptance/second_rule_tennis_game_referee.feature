Feature: Tennis game referee
  Manage a tennis game with score rule 2

  Background:
    Given use set score rule number 2

  Scenario Outline: Manage tennis game with the second game rules
    Given new game with firstPlayer has score equals "<scorePlayer1Started>" and secondPlayer has score equals "<scorePlayer2Started>"
    When firstPlayer marks a point
    Then firstPlayer score should be "<scorePlayer1Expected>"
    And the secondPlayer score should be "<scorePlayer2Expected>"

    Examples:
      | scorePlayer1Started | scorePlayer1Expected | scorePlayer2Started | scorePlayer2Expected |
      | 40                  | ADV                  | 40                  | 40                   |
      | 40                  | DEUCE                | ADV                 | DEUCE                |
      | DEUCE               | ADV                  | DEUCE               | 40                   |
      | ADV                 | 0                    | 40                  | 0                    |

  Scenario: Manage the DEUCE rule game
    Given new game with firstPlayer has score equals "ADV" and secondPlayer has score equals "40"
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
