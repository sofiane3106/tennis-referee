package com.rubix.tennis.referee.acceptance;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.Score;
import com.rubix.tennis.referee.domain.ScoreRule1;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {

    private Game game;
    private Player playerOne;
    private Player playerTwo;
    private Score scorePlayer;

    @Given("{int} players, player{int} and player{int} start a game")
    public void playersPlayerAndPlayerStartAGame(int arg0, int arg1, int arg2) {
        playerOne = Player.builder().firstName("Sofiane").lastName("REBIB").build();
        playerTwo = Player.builder().firstName("Sofiane").lastName("REBIB").build();
        scorePlayer = new ScoreRule1();
        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
    }

    @When("player{int} mark a point")
    public void playerMarkAPoint(int arg0) {
        game.getPlayerTwo().markPoint();

    }

    @Then("The player{int} score should increment")
    public void thePlayerScoreShouldIncrement(int arg0) {

    }

    @Given("player{int} has score = {int}")
    public void playerHasScore(int arg0, int arg1) {

    }

    @Then("The player{int} win the game")
    public void thePlayerWinTheGame(int arg0) {

    }

    @And("the game is over")
    public void theGameIsOver() {
    }
}
