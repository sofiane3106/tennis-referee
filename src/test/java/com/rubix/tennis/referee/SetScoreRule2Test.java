package com.rubix.tennis.referee;

import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.TennisGame;
import com.rubix.tennis.referee.domain.TennisSet;
import com.rubix.tennis.referee.rules.ScoreRule;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule2;
import com.rubix.tennis.referee.rules.ScoreRuleSetRule2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class SetScoreRule2Test {

    private Player playerOne;

    private Player playerTwo;

    private TennisGame tennisGame;

    private TennisSet tennisSet;

    private ScoreRule scoreSet = new ScoreRuleSetRule2();

    @BeforeEach
    void setup() {
        // Given
        playerOne = new Player("Sofiane", "REBIB");
        playerTwo = new Player("Player2", "Player2LastName");

        tennisSet = TennisSet.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreSet).build();
        tennisGame = tennisSet.startNewGame();
    }

    @DisplayName("When we start a new game in a new set")
    @Test
    void should_start_new_set_correctly() {
        // Given
        // the given and the when are in the before method

        // Then
        Assertions.assertNotNull(tennisGame);
        Assertions.assertNotNull(tennisGame.getPlayerOne());
        Assertions.assertNotNull(tennisGame.getPlayerTwo());
        Assertions.assertNotNull(tennisGame.getScore());
        Assertions.assertNotNull(tennisGame.getTennisSet());
        Assertions.assertEquals(1, tennisSet.getTennisGames().size());
    }

    @DisplayName("When we win a game the set score in incremented")
    @Test
    void should_set_mark_point_correctly() {
        // Given
        playerOne.setGameScore("40");

        // When
        tennisGame.markPoint(playerOne);

        // Then
        Assertions.assertEquals("0", playerOne.getGameScore());
        Assertions.assertEquals("0", playerTwo.getGameScore());
        Assertions.assertEquals(1, playerOne.getSetScore());
        Assertions.assertEquals(playerOne, tennisGame.getWinner());
    }

    @Test
    void should_get_correct_score() {
        // Given
        playerOne.setGameScore("40");
        playerTwo.setGameScore("15");

        // When
        tennisGame.markPoint(playerOne);
        String score = tennisSet.getScore();

        // Then
        assertEquals(score, "Player : Player(firstName=Sofiane, lastName=REBIB, gameScore=0, setScore=1, tieBreakScore=0, isMatchWinner=false) has setScore = 1\n" +
                "Player : Player(firstName=Player2, lastName=Player2LastName, gameScore=0, setScore=0, tieBreakScore=0, isMatchWinner=false) has setScore = 0");
        assertEquals(playerOne.getGameScore(), "0");
        assertEquals(playerTwo.getGameScore(), "0");
    }

    @DisplayName("The player score should increment 0, 15, 30, 40")
    @Test
    void when_player_marks_points_score_should_increment_correctly() {
        // Given
        IntStream.range(1, 4)
                .forEach(index -> {
                    // When
                    tennisGame.markPoint(playerOne);

                    // Then
                    verifyScore(index, playerOne);
                });
    }

    private void verifyScore(int index, Player playerOne) {
        assertEquals(ScoreRuleGameRule2.scorePoints.get(index), playerOne.getGameScore());
    }

    @Test
    void should_increment_set_score() {
        // Given

        // When
        scoreSet.incrementPlayerScore(playerOne, playerTwo);

        // Then
        assertEquals(1, playerOne.getSetScore());
        assertEquals(0, playerTwo.getSetScore());
    }

    @Test
    void should_increment_win_simple_set() {
        // Given
        playerOne.setSetScore(5);
        playerTwo.setSetScore(3);

        // When
        scoreSet.incrementPlayerScore(playerOne, playerTwo);

        // Then
        assertEquals(6, playerOne.getSetScore());
        assertEquals(3, playerTwo.getSetScore());
    }

    @Test
    void should_increment_deuce_adv_score() {
        // Given
        playerOne.setSetScore(5);
        playerTwo.setSetScore(5);

        // When
        scoreSet.incrementPlayerScore(playerOne, playerTwo);

        // Then
        assertEquals(6, playerOne.getSetScore());
        assertEquals(5, playerTwo.getSetScore());
    }

    @Test
    void should_increment_tie_break_score() {
        // Given
        playerOne.setSetScore(6);
        playerTwo.setSetScore(6);

        // When
        scoreSet.incrementPlayerScore(playerOne, playerTwo);

        // Then
        assertEquals(6, playerOne.getSetScore());
        assertEquals(6, playerTwo.getSetScore());
    }


    @Test
    void should_check_player_has_won_set() {
        // Given
        playerOne.setSetScore(6);
        playerTwo.setSetScore(5);

        // When
        boolean hasWon = scoreSet.hasWon(playerOne, playerTwo);

        // Then
        Assertions.assertTrue(hasWon);
    }

    @Test
    void should_check_player_has_not_won_set_tie_break() {
        // Given
        playerOne.setSetScore(6);
        playerTwo.setSetScore(6);

        // When
        boolean hasWon = scoreSet.hasWon(playerOne, playerTwo);

        // Then
        Assertions.assertFalse(hasWon);
    }

    @Test
    void should_check_player_has_not_won_set() {
        // Given
        playerOne.setSetScore(5);
        playerTwo.setSetScore(5);

        // When
        boolean hasWon = scoreSet.hasWon(playerOne, playerTwo);

        // Then
        Assertions.assertFalse(hasWon);
    }

    @Test
    void should_check_player_has_not_won_set_tie_break_increment() {
        // Given
        playerOne.setSetScore(6);
        playerTwo.setSetScore(6);

        // When
        boolean hasWon = scoreSet.hasWon(playerOne, playerTwo);

        // Then
        Assertions.assertFalse(hasWon);
    }

    @Test
    void should_check_player_has_won_set_tie_break_increment() {
        // Given
        playerOne.setSetScore(6);
        playerTwo.setSetScore(6);

        playerOne.setTieBreakScore(6);
        playerTwo.setTieBreakScore(2);

        // When
        boolean hasWon = scoreSet.hasWon(playerOne, playerTwo);

        // Then
        Assertions.assertTrue(hasWon);
    }
}
