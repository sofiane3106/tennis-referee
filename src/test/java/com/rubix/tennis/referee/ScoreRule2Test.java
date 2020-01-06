package com.rubix.tennis.referee;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.ScoreRule1;
import com.rubix.tennis.referee.domain.ScoreRule2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class ScoreRule2Test {

    @DisplayName("The player score should increment 0, 15, 30, 40")
    @Test
    void when_player_marks_points_score_should_increment_correctly() {
        // Given
        Player playerOne = new Player("Sofiane", "REBIB");
        Player playerTwo = new Player("Dupont", "LORELLA");
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        ScoreRule2 score = ScoreRule2.builder().game(game).build();


        IntStream.range(1, 4)
                .forEach(index -> {
                    // When
                    score.markPoint(playerOne);

                    // Then
                    verifyScore(index, playerOne);
                });
    }

    @DisplayName("When player has score equals 40 and the other one has less than 40 the the first one win the game")
    @Test
    void player_should_win_the_game() {
        // Given
        Player playerOne = new Player("Sofiane", "REBIB", "40");
        Player playerTwo = new Player("Dupont", "LORELLA", "30");
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        ScoreRule2 score = ScoreRule2.builder().game(game).build();

        // When
        score.markPoint(playerOne);

        // Then
        assertTrue(game.isOver());
        assertEquals(playerOne, game.getWinner());
    }

    @DisplayName("When the two players has score equals 40 and one player mark a point then his score will be ADV")
    @Test
    void player_score_should_be_adv() {
        // Given
        Player playerOne = new Player("Sofiane", "REBIB", "40");
        Player playerTwo = new Player("Dupont", "LORELLA", "40");
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        ScoreRule2 score = ScoreRule2.builder().game(game).build();

        // When
        score.markPoint(playerOne);

        // Then
        assertFalse(game.isOver());
        assertEquals(ScoreRule2.ADVANTAGE_POINT, playerOne.getScore());
    }

    @DisplayName("When one player has score 40 and other has ADV, when the first mark a point then the two players score will be DEUCE")
    @Test
    void player_score_should_be_deuce() {
        // Given
        Player playerOne = new Player("Sofiane", "REBIB", "40");
        Player playerTwo = new Player("Dupont", "LORELLA", ScoreRule2.ADVANTAGE_POINT);
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        ScoreRule2 score = ScoreRule2.builder().game(game).build();

        // When
        score.markPoint(playerOne);

        // Then
        assertFalse(game.isOver());
        assertEquals(ScoreRule2.DEUCE_POINT, playerOne.getScore());
        assertEquals(ScoreRule2.DEUCE_POINT, playerTwo.getScore());
    }

    private void verifyScore(int index, Player playerOne) {
        assertEquals(ScoreRule1.scorePoints.get(index), playerOne.getScore());
    }
}
