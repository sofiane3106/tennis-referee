Feature: Tennis game referee

  Background:
    Given use score rule number 4

  Scenario Outline: Manage the set rule game
    Given new tennisGame for a new set with firstPlayer has set score equals "<setScoreFirstPlayerStarted>" and secondPlayer has set score equals "<setScoreSecondPlayerStarted>"
    And firstPlayer has gameScore equals "<gameScoreFirstPlayerStarted>" and secondPlayer has gameScore equals "<gameScoreSecondPlayerStarted>"
    And firstPlayer has tieBreak score equals "<tieBreakScoreFirstPlayerStarted>" and secondPlayer has tieBreak score equals "<tieBreakScoreSecondPlayerStarted>"
    When firstPlayer marks a point
    Then tennisGame over equals "<gameOver>"
    And firstPlayer won the tennisGame equals "<firstPlayerWonTheGame>"
    And firstPlayer score should be "<gameScoreFirstPlayerExpected>"
    And the secondPlayer score should be "<gameScoreSecondPlayerExpected>"
    And set is over equals "<setOver>"
    And firstPlayer won the set equals "<firstPlayerWonTheSet>"
    And firstPlayer set score should be "<setScoreFirstPlayerExpected>"
    And secondPlayer set score should be "<setScoreSecondPlayerExpected>"
    And firstPlayer tieBreak score should be "<tieBreakScoreFirstPlayerExpected>"
    And secondPlayer tieBreak score should be "<tieBreakScoreSecondPlayerExpected>"

    Examples:
      | gameScoreFirstPlayerStarted | setScoreFirstPlayerStarted | tieBreakScoreFirstPlayerStarted | gameScoreFirstPlayerExpected | setScoreFirstPlayerExpected | tieBreakScoreFirstPlayerExpected | gameScoreSecondPlayerStarted | setScoreSecondPlayerStarted | tieBreakScoreSecondPlayerStarted | gameScoreSecondPlayerExpected | setScoreSecondPlayerExpected | tieBreakScoreSecondPlayerExpected | gameOver | setOver | firstPlayerWonTheGame | firstPlayerWonTheSet |
      | ADV                         | 0                          | 0                               | 0                            | 1                           | 0                                | 40                           | 0                           | 0                                | 0                             | 0                            | 0                                 | true     | false   | true                  | false                |
      | ADV                         | 5                          | 0                               | 0                            | 0                           | 0                                | 40                           | 4                           | 0                                | 0                             | 0                            | 0                                 | true     | true    | true                  | true                 |
      | ADV                         | 6                          | 0                               | 0                            | 6                           | 1                                | 40                           | 6                           | 0                                | 0                             | 6                            | 0                                 | true     | false   | true                  | false                |
      | ADV                         | 6                          | 2                               | 0                            | 6                           | 3                                | 40                           | 6                           | 1                                | 0                             | 6                            | 1                                 | true     | false   | true                  | false                |
      | ADV                         | 6                          | 5                               | 0                            | 6                           | 6                                | 40                           | 6                           | 5                                | 0                             | 6                            | 5                                 | true     | false   | true                  | false                |
      | ADV                         | 6                          | 6                               | 0                            | 6                           | 7                                | 40                           | 6                           | 6                                | 0                             | 6                            | 6                                 | true     | false   | true                  | false                |
      | ADV                         | 6                          | 6                               | 0                            | 7                           | 0                                | 40                           | 6                           | 2                                | 0                             | 6                            | 0                                 | true     | true    | true                  | true                |

