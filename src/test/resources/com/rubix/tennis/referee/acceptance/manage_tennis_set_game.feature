Feature: Tennis game referee
  Manage a tennis set game with score rule 1

  Scenario: Start a game with 2 players
    Given 2 players, player1 and player2 start a game
    When player1 mark a point
    Then The player1 score should increment

  Scenario: Player 2 win the game
    Given player2 has score = 40
    And player1 has score = 40
    When player2 mark a point
    Then The player2 win the game
    And  the game is over
