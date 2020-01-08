package com.rubix.tennis.referee;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.rules.ScoreRule;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class ScoreRule1Test {

    Player playerOne;
    Player playerTwo;
    Game game;

    @BeforeEach
    void setup() {
        // Given
        playerOne = new Player("Sofiane", "REBIB");
        playerTwo = new Player("Dupont", "LORELLA");
        ScoreRule scoreGame = new ScoreRuleGameRule1();
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

    @DisplayName("When player has score equals 40 and mark a point he wins the game")
    @Test
    void player_should_win_the_game() {
        // Given
        playerOne.setGameScore("40");
        playerTwo.setGameScore("40");

        // When
        game.markPoint(playerOne);

        // Then
        assertEquals(playerOne, game.getWinner());

    }

    private void verifyScore(int index, Player playerOne) {
        assertEquals(ScoreRuleGameRule1.scorePoints.get(index), playerOne.getGameScore());
    }

}
