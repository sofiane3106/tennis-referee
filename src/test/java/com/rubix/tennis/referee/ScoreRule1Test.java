package com.rubix.tennis.referee;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class ScoreRule1Test {

    @DisplayName("The player score should increment 0, 15, 30, 40")
    @Test
    void when_player_marks_points_score_should_increment_correctly() {
        // Given
        Player playerOne = new Player("Sofiane", "REBIB");
        Player playerTwo = new Player("Dupont", "LORELLA");
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        ScoreRule1 score = ScoreRule1.builder().game(game).build();


        IntStream.range(1, 4)
                .forEach(index -> {
                    // When
                    score.markPoint(playerOne);

                    // Then
                    verifyScore(index, playerOne);
                });
    }

    @DisplayName("When player has score equals 40 and mark a point he wins the game")
    @Test
    void player_should_win_the_game() {
        // Given
        Player playerOne = new Player("Sofiane", "REBIB", "40");
        Player playerTwo = new Player("Dupont", "LORELLA", "30");
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        ScoreRule1 score = ScoreRule1.builder().game(game).build();

        // When
        score.markPoint(playerOne);

        // Then
        assertEquals(playerOne, game.getWinner());

    }

    private void verifyScore(int index, Player playerOne) {
        assertEquals(ScoreRule1.scorePoints.get(index), playerOne.getScore());
    }

}
