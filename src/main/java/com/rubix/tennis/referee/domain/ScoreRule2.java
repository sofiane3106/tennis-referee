package com.rubix.tennis.referee.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
@Builder
public class ScoreRule2 extends Score {

    public static final List<String> scorePoints = Arrays.asList("0", "15", "30", "40");
    public static final String ADVANTAGE_POINT = "ADV";
    public static final String DEUCE_POINT = "DEUCE";

    private final Game game;

    @Override
    public void markPoint(Player player) {
        if (game.isOver()) {
            throw new RuntimeException("Game already over");
        } else if (hasWon(player)) {
            finishGame(player, game);
        } else {
            incrementPlayerScore(player);
        }
    }

    private void incrementPlayerScore(Player player) {
        Player otherPlayer = game.getPlayerOne().equals(player) ? game.getPlayerTwo() : game.getPlayerOne();
        String lastScore = scorePoints.get(scorePoints.size() - 1);
        String scorePlayer;
        String scoreOtherPlayer;

        if (player.getScore().equals(DEUCE_POINT) && otherPlayer.getScore().equals(DEUCE_POINT)) {
            scorePlayer = ADVANTAGE_POINT;
            scoreOtherPlayer = lastScore;
        } else if (player.getScore().equals(lastScore) && otherPlayer.getScore().equals(ADVANTAGE_POINT)) {
            scorePlayer = DEUCE_POINT;
            scoreOtherPlayer = DEUCE_POINT;
        } else if (player.getScore().equals(lastScore)) {
            scorePlayer = ADVANTAGE_POINT;
            scoreOtherPlayer = otherPlayer.getScore();
        } else {
            int i = scorePoints.indexOf(player.getScore());
            scorePlayer = scorePoints.get(++i);
            scoreOtherPlayer = otherPlayer.getScore();
        }

        player.setScore(scorePlayer);
        otherPlayer.setScore(scoreOtherPlayer);
    }

    private boolean hasWon(Player player) {
        Player otherPlayer = game.getPlayerOne().equals(player) ? game.getPlayerTwo() : game.getPlayerOne();
        String lastScore = scorePoints.get(scorePoints.size() - 1);

        return player.getScore().equals(ADVANTAGE_POINT) ||
                (player.getScore().equals(lastScore) && otherPlayer.getScore().compareTo(lastScore) < 0);
    }
}
