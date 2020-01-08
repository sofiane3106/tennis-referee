Feature: Tennis game referee
  Manage a tennis game with score rule 4

  Background:
    Given use set score rule number 2

  Scenario Outline: Manage tennis set game with the third game rules
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

  Scenario: Manage the set rule game
    Given new game for a new set with firstPlayer has score equals "ADV" and secondPlayer has score equals "40"
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And firstPlayer set score should be 1
    And secondPlayer set score should be 0
    And set is over equals false
    And no winner yet for this set

  ## all following scenarios need to be refactored with data table
  Scenario: Manage the set rule game
    Given new game for a new set with firstPlayer has set score equals 5 and secondPlayer has set score equals 4
    And firstPlayer has gameScore equals "ADV" and secondPlayer has gameScore equals "40"
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And set is over equals true
    And set winner is firstPlayer

  Scenario: Manage the Tie-Break set rule game
    Given new game for a new set with firstPlayer has set score equals 6 and secondPlayer has set score equals 6
    And firstPlayer has gameScore equals "ADV" and secondPlayer has gameScore equals "40"
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And set is over equals false
    And no winner yet for this set
    And firstPlayer set score should be 6
    And secondPlayer set score should be 6
    And firstPlayer tieBreak score should be 1
    And secondPlayer tieBreak score should be 0

  Scenario: Manage the Tie-Break set rule game
    Given new game for a new set with firstPlayer has set score equals 6 and secondPlayer has set score equals 6
    And firstPlayer has gameScore equals "ADV" and secondPlayer has gameScore equals "40"
    And firstPlayer has tieBreak score equals 2 and secondPlayer has tieBreak score equals 1
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And set is over equals false
    And no winner yet for this set
    And firstPlayer set score should be 6
    And secondPlayer set score should be 6
    And firstPlayer tieBreak score should be 3
    And secondPlayer tieBreak score should be 1

  Scenario: Manage the Tie-Break set rule game
    Given new game for a new set with firstPlayer has set score equals 6 and secondPlayer has set score equals 6
    And firstPlayer has gameScore equals "ADV" and secondPlayer has gameScore equals "40"
    And firstPlayer has tieBreak score equals 5 and secondPlayer has tieBreak score equals 5
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And set is over equals false
    And no winner yet for this set
    And firstPlayer set score should be 6
    And secondPlayer set score should be 6
    And firstPlayer tieBreak score should be 6
    And secondPlayer tieBreak score should be 5

  Scenario: Manage the Tie-Break set rule game
    Given new game for a new set with firstPlayer has set score equals 6 and secondPlayer has set score equals 6
    And firstPlayer has gameScore equals "ADV" and secondPlayer has gameScore equals "40"
    And firstPlayer has tieBreak score equals 5 and secondPlayer has tieBreak score equals 5
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And set is over equals false
    And no winner yet for this set
    And firstPlayer set score should be 6
    And secondPlayer set score should be 6
    And firstPlayer tieBreak score should be 6
    And secondPlayer tieBreak score should be 5

  Scenario: Manage the Tie-Break set rule game
    Given new game for a new set with firstPlayer has set score equals 6 and secondPlayer has set score equals 6
    And firstPlayer has gameScore equals "ADV" and secondPlayer has gameScore equals "40"
    And firstPlayer has tieBreak score equals 6 and secondPlayer has tieBreak score equals 6
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And set is over equals false
    And no winner yet for this set
    And firstPlayer set score should be 6
    And secondPlayer set score should be 6
    And firstPlayer tieBreak score should be 7
    And secondPlayer tieBreak score should be 6

  Scenario: Manage the Tie-Break set rule game
    Given new game for a new set with firstPlayer has set score equals 6 and secondPlayer has set score equals 6
    And firstPlayer has gameScore equals "ADV" and secondPlayer has gameScore equals "40"
    And firstPlayer has tieBreak score equals 7 and secondPlayer has tieBreak score equals 6
    When firstPlayer marks a point
    Then game over
    And firstPlayer won the game
    And firstPlayer score should be "0"
    And the secondPlayer score should be "0"
    And set is over equals true
    And set winner is firstPlayer
    And firstPlayer set score should be 7
    And secondPlayer set score should be 6
    And firstPlayer tieBreak score should be 0
    And secondPlayer tieBreak score should be 0


