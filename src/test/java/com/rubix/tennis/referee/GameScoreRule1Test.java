package com.rubix.tennis.referee;

import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.TennisGame;
import com.rubix.tennis.referee.rules.ScoreRule;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule1;
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
class GameScoreRule1Test {

    private Player playerOne;

    private Player playerTwo;

    private TennisGame tennisGame;

    private ScoreRule scoreGame = new ScoreRuleGameRule1();;

    @BeforeEach
    void setup() {
        // Given
        playerOne = new Player("Sofiane", "REBIB");
        playerTwo = new Player("Dupont", "LORELLA");
        tennisGame = TennisGame.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreGame).build();
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

    @DisplayName("When player has score equals 40 and mark a point he wins the tennisGame")
    @Test
    void player_should_win_the_game() {
        // Given
        playerOne.setGameScore("40");
        playerTwo.setGameScore("40");

        // When
        tennisGame.markPoint(playerOne);

        // Then
        assertEquals(playerOne, tennisGame.getWinner());
        assertEquals(playerOne.getGameScore(), "0");
        assertEquals(playerTwo.getGameScore(), "0");
    }

    @Test
    void should_get_correct_score() {
        // Given
        playerOne.setGameScore("15");
        playerTwo.setGameScore("15");

        // When
        tennisGame.markPoint(playerOne);
        String score = tennisGame.getScore();

        // Then
        assertEquals(score, "Player : Player(firstName=Sofiane, lastName=REBIB, gameScore=30, setScore=0, tieBreakScore=0, isMatchWinner=false) has gameScore = 30\n" +
                "Player : Player(firstName=Dupont, lastName=LORELLA, gameScore=15, setScore=0, tieBreakScore=0, isMatchWinner=false) has gameScore = 15");
        assertEquals(playerOne.getGameScore(), "30");
        assertEquals(playerTwo.getGameScore(), "15");
    }

    private void verifyScore(int index, Player playerOne) {
        assertEquals(ScoreRuleGameRule1.scorePoints.get(index), playerOne.getGameScore());
    }

    @Test
    void should_increment_score() {
        // Given
        playerOne.setGameScore("15");
        playerTwo.setGameScore("15");

        // When
        scoreGame.incrementPlayerScore(playerOne, playerTwo);

        // Then
         assertEquals(playerOne.getGameScore(), "30");
        assertEquals(playerTwo.getGameScore(), "15");
    }

    @Test
    void should_check_player_has_won() {
        // Given
        playerOne.setGameScore("40");
        playerTwo.setGameScore("15");

        // When
        boolean hasWon = scoreGame.hasWon(playerOne, playerTwo);
        boolean secondPlayerHasWon = scoreGame.hasWon(playerTwo, playerOne);

        // Then
        Assertions.assertTrue(hasWon);
        Assertions.assertFalse(secondPlayerHasWon);
    }
}
