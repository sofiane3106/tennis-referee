package com.rubix.tennis.referee;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.rules.ScoreRule;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule1;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class ScoreRule2Test {

    Player playerOne;
    Player playerTwo;
    Game game;

    @BeforeEach
    void setup() {
        // Given
        playerOne = new Player("Sofiane", "REBIB");
        playerTwo = new Player("Dupont", "LORELLA");
        ScoreRule scoreGame = new ScoreRuleGameRule2();
        game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreGame).build();
    }

    @DisplayName("The player score should increment 0, 15, 30, 40")
    @Test
    void when_player_marks_points_score_should_increment_correctly() {
        // Given

        IntStream.range(1, 4)
                .forEach(index -> {
                    // When
                    game.markPoint(playerOne);

                    // Then
                    verifyScore(index, playerOne);
                });
    }

    @DisplayName("When player has score equals 40 and the other one has less than 40 the the first one win the game")
    @Test
    void player_should_win_the_game() {
        // Given
        playerOne.setGameScore("40");
        playerTwo.setGameScore("15");

        // When
        game.markPoint(playerOne);

        // Then
        assertTrue(game.isOver());
        assertEquals(playerOne, game.getWinner());
    }

    @DisplayName("When the two players has score equals 40 and one player mark a point then his score will be ADV")
    @Test
    void player_score_should_be_adv() {
        // Given
        playerOne.setGameScore("40");
        playerTwo.setGameScore("40");

        // When
        game.markPoint(playerOne);

        // Then
        assertFalse(game.isOver());
        assertEquals(ScoreRuleGameRule2.ADVANTAGE_POINT, playerOne.getGameScore());
    }

    @DisplayName("When one player has score 40 and other has ADV, when the first mark a point then the two players score will be DEUCE")
    @Test
    void player_score_should_be_deuce() {
        // Given
        playerOne.setGameScore("40");
        playerTwo.setGameScore(ScoreRuleGameRule2.ADVANTAGE_POINT);

        // When
        game.markPoint(playerOne);

        // Then
        assertFalse(game.isOver());
        assertEquals(ScoreRuleGameRule2.DEUCE_POINT, playerOne.getGameScore());
        assertEquals(ScoreRuleGameRule2.DEUCE_POINT, playerTwo.getGameScore());
    }

    private void verifyScore(int index, Player playerOne) {
        assertEquals(ScoreRuleGameRule2.scorePoints.get(index), playerOne.getGameScore());
    }
}
