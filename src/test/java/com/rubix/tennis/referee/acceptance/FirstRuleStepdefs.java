package com.rubix.tennis.referee.acceptance;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.Score;
import com.rubix.tennis.referee.domain.ScoreRule1;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstRuleStepdefs {

    private Game game;
    private Player playerOne;
    private Player playerTwo;
    private Score score;

    @Given("player has score equals {string}")
    public void playerHasScoreEqualsStart(String playerScoreStarted) {
        playerOne = Player.builder().firstName("Sofiane").lastName("REBIB").score(playerScoreStarted).build();
        playerTwo = Player.builder().firstName("Player2").lastName("Player2LastName").score(playerScoreStarted).build();

        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        score = ScoreRule1.builder().game(game).build();
    }

    @When("player mark a point")
    public void playerMarkAPoint() {
        score.markPoint(playerOne);
    }

    @Then("the player score should be {string}")
    public void thePlayerScoreShouldBePlayerScoreExpected(String expectedScore) {
        assertEquals(expectedScore, playerOne.getScore());
    }

    @Given("{string} has score equals {string}")
    public void hasScoreEquals(String player, String scoreStarted) {
        playerOne = Player.builder().firstName(player).lastName("REBIB").score(scoreStarted).build();

    }

    @And("{string} score is {string}")
    public void scoreIs(String player, String scoreStarted) {
        playerTwo = Player.builder().firstName(player).lastName("Player2LastName").score(scoreStarted).build();
        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        score = ScoreRule1.builder().game(game).build();
    }

    @When("{string} mark a point")
    public void markAPoint(String playerFirstName) {
        Player player = playerOne.getFirstName().equals(playerFirstName) ? playerOne : playerTwo;
        score.markPoint(player);
    }

    @Then("the {string} score should be {string}")
    public void theScoreShouldBe(String playerFirstName, String expectedScore) {
        Player player = playerOne.getFirstName().equals(playerFirstName) ? playerOne : playerTwo;
        assertEquals(expectedScore, player.getScore());
    }

    @Then("The {string} win the game")
    public void theWinTheGame(String playerFirstName) {
        assertEquals(game.getWinner().getFirstName(), playerFirstName);
    }

    @And("the game is over")
    public void theGameIsOver() {
        assertTrue(game.isOver());
    }


}
