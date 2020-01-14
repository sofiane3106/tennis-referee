Feature: Tennis game referee

  Background:
    Given use score rule number 1

  Scenario Outline: Manage a tennis game with rule 1
    Given player has score equals "<start>"
    When firstPlayer marks a point
    Then firstPlayer score should be "<expect>"

    Examples:
      | start | expect |
      | 0     | 15     |
      | 15    | 30     |
      | 30    | 40     |
