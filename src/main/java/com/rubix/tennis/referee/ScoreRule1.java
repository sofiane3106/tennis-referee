package com.rubix.tennis.referee;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
@Builder
public class ScoreRule1 extends Score {

    public static final List<String> scorePoints = Arrays.asList("0", "15", "30", "40");

    private final Game game;

    @Override
    public void markPoint(Player player) {
        if (game.isOver()) {
            throw new RuntimeException("Game already over");
        } else if (hasWon(player.getScore())) {
            finishGame(player, game);
        } else {
            incrementPlayerScore(player);
        }
    }

    private void incrementPlayerScore(Player player) {
        int i = scorePoints.indexOf(player.getScore());
        String newScore = scorePoints.get(++i);
        player.setScore(newScore);
    }

    private boolean hasWon(String score) {
        return score.equals(scorePoints.get(scorePoints.size() - 1));
    }
}
