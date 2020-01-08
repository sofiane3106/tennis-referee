package com.rubix.tennis.referee.acceptance;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.TennisSet;
import com.rubix.tennis.referee.rules.ScoreRule;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule2;
import com.rubix.tennis.referee.rules.ScoreRuleSetRule1;
import com.rubix.tennis.referee.rules.ScoreRuleSetRule2;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class OtherRulesStepDefs {

    private Game game;
    private Player playerOne;
    private Player playerTwo;
    private ScoreRule scoreRule = new ScoreRuleGameRule2();

    private TennisSet set;
    private ScoreRule tennisSetScoreRule;

    @Given("new game with firstPlayer has score equals {string} and secondPlayer has score equals {string}")
    public void newGameWithFirstPlayerHasScoreEqualsAndSecondPlayerHasScoreEquals(String fistPlayerStartedGame, String secondPlayerStartedScore) {
        playerOne = new Player("Sofiane", "REBIB", fistPlayerStartedGame);
        playerTwo = new Player("Player2", "Player2LastName", secondPlayerStartedScore);
        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreRule).build();
    }

    @Given("use set score rule number {int}")
    public void useSetScoreRuleNumber(int scoreRuleId) {
        switch (scoreRuleId) {
            case 1:
                tennisSetScoreRule = new ScoreRuleSetRule1();
                break;
            case 2:
                tennisSetScoreRule = new ScoreRuleSetRule2();
                break;
            default:
                break;
        }
    }

    @When("firstPlayer marks a point")
    public void firstPlayerMarkAPoint() {
        game.markPoint(playerOne);
    }

    @And("the secondPlayer score should be {string}")
    public void theSecondPlayerScoreShouldBe(String expectedScore) {
        assertEquals(expectedScore, playerTwo.getGameScore());
    }

    @Then("firstPlayer score should be {string}")
    public void firstPlayerScoreShouldBe(String expectedScore) {
        assertEquals(expectedScore, playerOne.getGameScore());
    }

    @And("firstPlayer won the game")
    public void firstPlayerWonTheGame() {
        assertEquals(playerOne, game.getWinner());
    }

    @Then("game over")
    public void gameOver() {
        assertTrue(game.isOver());
    }

    @Given("new game for a new set with firstPlayer has score equals {string} and secondPlayer has score equals {string}")
    public void newGameForANewSetWithFirstPlayerHasScoreEqualsAndSecondPlayerHasScoreEquals(String fistPlayerStartedGame, String secondPlayerStartedScore) {
        playerOne = new Player("Sofiane", "REBIB", fistPlayerStartedGame);
        playerTwo = new Player("Player2", "Player2LastName", secondPlayerStartedScore);

        set = TennisSet.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(tennisSetScoreRule).build();
        log.debug(set.toString());
        game = set.startNewGame();
    }

    @And("set is over equals {string}")
    public void setIsOverEquals(String isOver) {
        assertEquals(isOver, String.valueOf(set.isOver()));
    }

    // TODO : to refacto pass a boolean parameter instead of false
    @And("set is over equals false")
    public void setIsOverEqualsFalse() {
        Assertions.assertFalse(set.isOver());
    }

    @And("no winner yet for this set")
    public void noWinnerYetForThisSet() {
        Assertions.assertNull(set.getWinner());
    }

    @And("firstPlayer set score should be {int}")
    public void firstPlayerSetScoreShouldBe(int setScoreExpected) {
        Assertions.assertEquals(setScoreExpected, playerOne.getSetScore());
    }

    @Given("new game for a new set with firstPlayer has set score equals {int} and secondPlayer has set score equals {int}")
    public void newGameForANewSetWithFirstPlayerHasSetScoreEqualsAndSecondPlayerHasSetScoreEquals(int firstPlayerSetScoreStarted, int secondPlayerSetScoreStarted) {
        playerOne = new Player("Sofiane", "REBIB", "0", firstPlayerSetScoreStarted);
        playerTwo = new Player("Player2", "Player2LastName", "0", secondPlayerSetScoreStarted);

        set = TennisSet.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(tennisSetScoreRule).build();
        game = set.startNewGame();
    }

    @And("set is over equals true")
    public void setIsOverEqualsTrue() {
        Assertions.assertTrue(set.isOver());
    }

    @And("set winner is firstPlayer")
    public void setWinnerIsFirstPlayer() {
        Assertions.assertEquals(playerOne, set.getWinner());
    }

    @And("secondPlayer set score should be {int}")
    public void secondplayerSetScoreShouldBe(int secondSetScoreExpected) {
        Assertions.assertEquals(secondSetScoreExpected, playerTwo.getSetScore());
    }

    @And("firstPlayer tieBreak score should be {int}")
    public void firstplayerTieBreakScoreShouldBe(int firstPlayerTieBreakScoreExpected) {
        Assertions.assertEquals(firstPlayerTieBreakScoreExpected, playerOne.getTieBreakScore());
    }

    @And("secondPlayer tieBreak score should be {int}")
    public void secondplayerTieBreakScoreShouldBe(int secondPlayerTieBreakScoreExpected) {
        Assertions.assertEquals(secondPlayerTieBreakScoreExpected, playerTwo.getTieBreakScore());
    }

    @And("firstPlayer has gameScore equals {string} and secondPlayer has gameScore equals {string}")
    public void firstplayerHasGameScoreEqualsAndSecondPlayerHasGameScoreEquals(String firstPlayerGameScoreStarted, String secondPlayerGameScoreStarted) {
        playerOne.setGameScore(firstPlayerGameScoreStarted);
        playerTwo.setGameScore(secondPlayerGameScoreStarted);
    }

    @And("firstPlayer has tieBreak score equals {int} and secondPlayer has tieBreak score equals {int}")
    public void firstplayerHasTieBreakScoreEqualsAndSecondPlayerHasSetScoreEquals(int firstPlayerTieBreakStarted, int secondPlayerTieBreakStarted) {
        playerOne.setTieBreakScore(firstPlayerTieBreakStarted);
        playerTwo.setTieBreakScore(secondPlayerTieBreakStarted);
    }
}
