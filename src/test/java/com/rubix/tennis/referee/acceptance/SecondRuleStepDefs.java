package com.rubix.tennis.referee.acceptance;

import com.rubix.tennis.referee.Game;
import com.rubix.tennis.referee.Player;
import com.rubix.tennis.referee.ScoreRule2;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecondRuleStepDefs {

    private Game game;
    private Player playerOne;
    private Player playerTwo;
    private ScoreRule2 score;

    @Given("players has score equals {string}")
    public void playersHasScoreEquals(String startedScore) {
        playerOne = new Player("Sofiane", "REBIB", startedScore);
        playerTwo = new Player("Player2", "Player2LastName", startedScore);

        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        score = ScoreRule2.builder().game(game).build();
    }

    @Given("firstPlayer has score equals {string}")
    public void firstplayerHasScoreEquals(String startedScore) {
        playerOne = new Player("Sofiane", "REBIB", startedScore);
    }

    @And("secondPlayer has score equals {string}")
    public void secondplayerHasScoreEquals(String startedScore) {
        playerTwo = new Player("Player2", "Player2LastName", startedScore);

        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        score = ScoreRule2.builder().game(game).build();
    }

    @When("firstPlayer mark a point")
    public void firstplayerMarkAPoint() {
        score.markPoint(playerOne);
    }

    @Then("the firstPlayer score should be {string}")
    public void theFirstPlayerScoreShouldBe(String expectedScore) {
        assertEquals(expectedScore, playerOne.getScore());
    }

    @And("the secondPlayer score should be {string}")
    public void theSecondPlayerScoreShouldBe(String expectedScore) {
        assertEquals(expectedScore, playerTwo.getScore());

    }

    @And("firstPlayer won the game")
    public void firstplayerWonTheGame() {
        assertEquals(playerOne, game.getWinner());
    }

    @When("firstPlayer marks a point")
    public void firstPlayerMarksAPoint() {
        score.markPoint(playerOne);
    }

    @Then("firstPlayer score should be {string}")
    public void firstPlayerScoreShouldBe(String expectedScore) {
        assertEquals(expectedScore, playerOne.getScore());
    }

    @Then("game over")
    public void gameOver() {
        assertTrue(game.isOver());
    }


}
