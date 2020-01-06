Feature: Tennis game referee
  Manage a tennis set game with score rule 1

  Scenario Outline: Manage the first set game
    Given player has score equals "<start>"
    When player mark a point
    Then the player score should be "<expect>"

    Examples:
      | start | expect |
      | 0     | 15     |
      | 15    | 30     |
      | 30    | 40     |

  Scenario Outline: Manage the first set game
    Given "<firstPlayer>" has score equals "<scorePlayer1Started>"
    And "<secondPlayer>" score is "<scorePlayer2Started>"
    When "<secondPlayer>" mark a point
    Then the "<secondPlayer>" score should be "<scorePlayer2Expected>"
    And the "<firstPlayer>" score should be "<scorePlayer1Expected>"

    Examples:
      | firstPlayer | scorePlayer1Started | scorePlayer1Expected | secondPlayer | scorePlayer2Started | scorePlayer2Expected |
      | sofiane     | 15                  | 15                   | dupont       | 15                  | 30                   |
      | sofiane     | 15                  | 15                   | dupont       | 30                  | 40                   |


  Scenario Outline: Manage the first set game
    Given "<firstPlayer>" has score equals "<scorePlayer1Started>"
    And "<secondPlayer>" has score equals "<scorePlayer2Started>"
    When "<secondPlayer>" mark a point
    Then The "<secondPlayer>" win the game
    And  the game is over

    Examples:
      | firstPlayer | scorePlayer1Started | secondPlayer | scorePlayer2Started |
      | sofiane     | 40                  | dupont       | 40                  |
