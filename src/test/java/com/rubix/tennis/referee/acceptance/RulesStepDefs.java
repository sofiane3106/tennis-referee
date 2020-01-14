package com.rubix.tennis.referee.acceptance;

import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.TennisGame;
import com.rubix.tennis.referee.domain.TennisSet;
import com.rubix.tennis.referee.rules.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class RulesStepDefs {

    //TODO: refacto test class

    private Player playerOne;
    private Player playerTwo;

    private TennisGame tennisGame;
    private ScoreRule tennisGameScoreRule;

    private TennisSet set;
    private ScoreRule tennisSetScoreRule;

    @Given("new tennisGame with firstPlayer has score equals {string} and secondPlayer has score equals {string}")
    public void newGameWithFirstPlayerHasScoreEqualsAndSecondPlayerHasScoreEquals(String fistPlayerStartedGame, String secondPlayerStartedScore) {
        playerOne = new Player("Sofiane", "REBIB", fistPlayerStartedGame);
        playerTwo = new Player("Player2", "Player2LastName", secondPlayerStartedScore);
        tennisGame = TennisGame.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(tennisGameScoreRule).build();
    }

    @Given("use score rule number {int}")
    public void useSetScoreRuleNumber(int scoreRuleId) {
        switch (scoreRuleId) {
            case 1:
                tennisGameScoreRule = new ScoreRuleGameRule1();
                break;
            case 2:
                tennisGameScoreRule = new ScoreRuleGameRule2();
                break;
            case 3:
                tennisSetScoreRule = new ScoreRuleSetRule1();
                break;
            case 4:
                tennisSetScoreRule = new ScoreRuleSetRule2();
                break;
            default:
                break;
        }
    }

    @When("firstPlayer marks a point")
    public void firstPlayerMarkAPoint() {
        tennisGame.markPoint(playerOne);
    }

    @And("the secondPlayer score should be {string}")
    public void theSecondPlayerScoreShouldBe(String expectedScore) {
        assertEquals(expectedScore, playerTwo.getGameScore());
    }

    @Then("firstPlayer score should be {string}")
    public void firstPlayerScoreShouldBe(String expectedScore) {
        assertEquals(expectedScore, playerOne.getGameScore());
    }

    @And("firstPlayer won the tennisGame equals {string}")
    public void firstPlayerWonTheGame(String firstPlayerWonTheGame) {
        assertEquals(Boolean.valueOf(firstPlayerWonTheGame), playerOne.equals(tennisGame.getWinner()));
    }

    @And("firstPlayer won the set equals {string}")
    public void firstplayerWonTheSetEquals(String firstPlayerWonTheSet) {
        assertEquals(Boolean.valueOf(firstPlayerWonTheSet), playerOne.equals(set.getWinner()));
    }

    @Then("tennisGame over equals {string}")
    public void gameOver(String gameOver) {
        assertEquals(Boolean.valueOf(gameOver), tennisGame.isOver());
    }

    @Given("new tennisGame for a new set with firstPlayer has score equals {string} and secondPlayer has score equals {string}")
    public void newGameForANewSetWithFirstPlayerHasScoreEqualsAndSecondPlayerHasScoreEquals(String fistPlayerStartedGame, String secondPlayerStartedScore) {
        playerOne = new Player("Sofiane", "REBIB", fistPlayerStartedGame);
        playerTwo = new Player("Player2", "Player2LastName", secondPlayerStartedScore);

        set = TennisSet.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(tennisSetScoreRule).build();
        tennisGame = set.startNewGame();
    }

    @And("set is over equals {string}")
    public void setIsOverEquals(String isOver) {
        assertEquals(isOver, String.valueOf(set.isOver()));
    }

    @Given("new tennisGame for a new set with firstPlayer has set score equals {string} and secondPlayer has set score equals {string}")
    public void newGameForANewSetWithFirstPlayerHasSetScoreEqualsAndSecondPlayerHasSetScoreEquals(String firstPlayerSetScoreStarted, String secondPlayerSetScoreStarted) {
        playerOne = new Player("Sofiane", "REBIB", "0", Integer.valueOf(firstPlayerSetScoreStarted));
        playerTwo = new Player("Player2", "Player2LastName", "0", Integer.valueOf(secondPlayerSetScoreStarted));

        set = TennisSet.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(tennisSetScoreRule).build();
        tennisGame = set.startNewGame();
    }

    @And("secondPlayer tieBreak score should be {string}")
    public void secondplayerTieBreakScoreShouldBe(String secondPlayerTieBreakScoreExpected) {
        Assertions.assertEquals(Integer.valueOf(secondPlayerTieBreakScoreExpected), playerTwo.getTieBreakScore());
    }

    @And("firstPlayer has gameScore equals {string} and secondPlayer has gameScore equals {string}")
    public void firstplayerHasGameScoreEqualsAndSecondPlayerHasGameScoreEquals(String firstPlayerGameScoreStarted, String secondPlayerGameScoreStarted) {
        playerOne.setGameScore(firstPlayerGameScoreStarted);
        playerTwo.setGameScore(secondPlayerGameScoreStarted);
    }

    @And("firstPlayer has tieBreak score equals {string} and secondPlayer has tieBreak score equals {string}")
    public void firstplayerHasTieBreakScoreEqualsAndSecondPlayerHasSetScoreEquals(String firstPlayerTieBreakStarted, String secondPlayerTieBreakStarted) {
        playerOne.setTieBreakScore(Integer.valueOf(firstPlayerTieBreakStarted));
        playerTwo.setTieBreakScore(Integer.valueOf(secondPlayerTieBreakStarted));
    }

    @And("firstPlayer set score should be {string}")
    public void firstplayerSetScoreShouldBe(String setScoreFirstPlayerExpected) {
        Assertions.assertEquals(Integer.valueOf(setScoreFirstPlayerExpected), playerOne.getSetScore());
    }

    @And("secondPlayer set score should be {string}")
    public void secondplayerSetScoreShouldBe(String setScoreSecondPlayerExpected) {
        Assertions.assertEquals(Integer.valueOf(setScoreSecondPlayerExpected), playerTwo.getSetScore());
    }

    @And("firstPlayer tieBreak score should be {string}")
    public void firstplayerTieBreakScoreShouldBe(String tieBreakScoreFirstPlayerExpected) {
        Assertions.assertEquals(Integer.valueOf(tieBreakScoreFirstPlayerExpected), playerOne.getTieBreakScore());
    }

    @Given("player has score equals {string}")
    public void playerHasScoreEquals(String playerScoreStarted) {
        playerOne = new Player("Sofiane", "REBIB", playerScoreStarted);
        playerTwo = new Player("Player2", "Player2LastName", "0");
        tennisGame = TennisGame.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(tennisGameScoreRule).build();
    }
}
