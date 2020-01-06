package com.rubix.tennis.referee.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

import static com.rubix.tennis.referee.domain.ScoreRule1.FIRST_SCORE_RULE;

@Slf4j
@Getter
@Builder
//@Named(FIRST_SCORE_RULE)
public class ScoreRule1 implements Score {

    public static final String FIRST_SCORE_RULE = "firstScoreRule";
    private static final List<String> scorePoints = Arrays.asList("0", "15", "30", "40");

    private final Game game;

    @Override
    public void markPoint(Player player) {

        if (game.isOver()) {
            // TODO exception to be created
            throw new RuntimeException("Game already over");
        }

        String score = player.getScore();

        if (hasWon(score)) {
            finishGame(player);
        } else {
            int i = scorePoints.indexOf(score);
            String newScore = scorePoints.get(++i);
            player.setScore(newScore);
        }
    }

    private void finishGame(Player player) {
        game.setOver(true);
        game.setWinner(player);
        log.info("{} has won the game", player.getFirstName());
        player.setScore(scorePoints.get(0));
    }

    @Override
    public String getGameScore() {
        StringBuilder gameScore = new StringBuilder();
        Player playerOne = game.getPlayerOne();
        Player playerTwo = game.getPlayerTwo();
        gameScore.append("Player : ").append(playerOne.toString()).append(" has score = ").append(playerOne.getScore()).append("\\n");
        gameScore.append("Player : ").append(playerTwo.toString()).append(" has score = ").append(playerTwo.getScore()).append("\\n");
        return gameScore.toString();
    }

    private boolean hasWon(String score) {
        return score.equals(scorePoints.get(scorePoints.size() - 1));
    }
}
