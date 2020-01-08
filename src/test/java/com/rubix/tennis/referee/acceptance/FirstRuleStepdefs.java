package com.rubix.tennis.referee.acceptance;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule1;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FirstRuleStepdefs {

    private Game game;
    private Player playerOne;
    private Player playerTwo;
    private ScoreRuleGameRule1 scoreRule;

    @Given("player has score equals {string}")
    public void playerHasScoreEqualsStart(String playerScoreStarted) {
        playerOne = new Player("Sofiane", "REBIB", playerScoreStarted);
        playerTwo = new Player("Player2", "Player2LastName", playerScoreStarted);
        scoreRule = new ScoreRuleGameRule1();

        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreRule).build();
    }

    @When("player mark a point")
    public void playerMarkAPoint() {
        game.markPoint(playerOne);
    }

    @Then("the player score should be {string}")
    public void thePlayerScoreShouldBePlayerScoreExpected(String expectedScore) {
        assertEquals(expectedScore, playerOne.getGameScore());
    }

    @Given("{string} has score equals {string}")
    public void hasScoreEquals(String playerFirstName, String scoreStarted) {
        playerOne = new Player(playerFirstName, "REBIB", scoreStarted);
    }

    @When("{string} mark a point")
    public void markAPoint(String playerFirstName) {
        Player player = playerOne.getFirstName().equals(playerFirstName) ? playerOne : playerTwo;
        game.markPoint(player);
    }

    @Then("the {string} score should be {string}")
    public void theScoreShouldBe(String playerFirstName, String expectedScore) {
        Player player = playerOne.getFirstName().equals(playerFirstName) ? playerOne : playerTwo;
        assertEquals(expectedScore, player.getGameScore());
    }

    @And("the game is over")
    public void theGameIsOver() {
        assertTrue(game.isOver());
    }

    @And("{string} score is {string}")
    public void scoreIs(String secondPlayerFirstName, String scoreStarted) {
        playerTwo = new Player(secondPlayerFirstName, "Player2LastName", scoreStarted);
        scoreRule = new ScoreRuleGameRule1();
        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreRule).build();
    }

    @Then("{string} win the game")
    public void winTheGame(String playerFirstName) {
        assertEquals(game.getWinner().getFirstName(), playerFirstName);
    }
}
