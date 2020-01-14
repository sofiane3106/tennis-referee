package com.rubix.tennis.referee.rules;

import com.rubix.tennis.referee.domain.Player;

public class ScoreRuleSetRule2 implements ScoreRule {

    @Override
    public void incrementPlayerScore(Player player, Player otherPlayer) {
        if (tieBreakIsActivated(player, otherPlayer)) {
            player.incrementTieBreakScore();
        } else {
            player.incrementSetScore();
        }
    }

    @Override
    public boolean hasWon(Player player, Player otherPlayer) {
        return checkSimpleSetRule(player, otherPlayer) || checkTieBreakRule(player, otherPlayer);
    }

    private boolean tieBreakIsActivated(Player player, Player otherPlayer) {
        return player.getSetScore() == 6 && otherPlayer.getSetScore() == 6;
    }

    private boolean checkTieBreakRule(Player player, Player otherPlayer) {
        return (player.getTieBreakScore() > 5 && (player.getTieBreakScore() - otherPlayer.getTieBreakScore()) > 0);
    }

    private boolean checkSimpleSetRule(Player player, Player otherPlayer) {
        return ((player.getSetScore() == 5 && otherPlayer.getSetScore() < 5) || (player.getSetScore() == 6 && otherPlayer.getSetScore() < 6));
    }
}
