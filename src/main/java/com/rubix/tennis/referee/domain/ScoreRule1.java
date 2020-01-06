package com.rubix.tennis.referee.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class ScoreRule1 extends Score {

    private static final List<String> scorePoints = Arrays.asList("0", "15", "30", "40");

    @Builder
    ScoreRule1(Player player, Game game) {
        super(player, game);
    }

    @Override
    protected void markPoint() {
        String s = scorePoints.get(this.getGamePoint());

        if (hasWon()) {
            this.getGame().setOver(true);

        }
    }

    private boolean hasWon() {
        return (this.getGamePoint() == (scorePoints.size() - 1));
    }
}
