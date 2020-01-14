Feature: Tennis game referee

  Background:
    Given use score rule number 2

  Scenario Outline: Manage tennis set game with the third game rules
    Given new tennisGame with firstPlayer has score equals "<scorePlayer1Started>" and secondPlayer has score equals "<scorePlayer2Started>"
    When firstPlayer marks a point
    Then firstPlayer score should be "<scorePlayer1Expected>"
    And the secondPlayer score should be "<scorePlayer2Expected>"

    Examples:
      | scorePlayer1Started | scorePlayer1Expected | scorePlayer2Started | scorePlayer2Expected |
      | 40                  | ADV                  | 40                  | 40                   |
      | 40                  | DEUCE                | ADV                 | DEUCE                |
      | DEUCE               | ADV                  | DEUCE               | 40                   |
      | ADV                 | 0                    | 40                  | 0                    |
