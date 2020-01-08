package com.rubix.tennis.referee.rules;

import com.rubix.tennis.referee.domain.Player;

public class ScoreRuleSetRule2 extends ScoreRule {

    @Override
    public void incrementPlayerScore(Player player, Player otherPlayer) {
        if (!tieBreakIsActivated(player, otherPlayer)) {
            player.incrementSetScore();
        }
    }

    private boolean tieBreakIsActivated(Player player, Player otherPlayer) {
        return player.getSetScore() == 6 && otherPlayer.getSetScore() == 6;
    }

    @Override
    public boolean hasWon(Player player, Player otherPlayer) {
        return checkSimpleSetRule(player, otherPlayer) || tieBreakRule(player, otherPlayer);
    }

    private boolean tieBreakRule(Player player, Player otherPlayer) {
        if (player.getTieBreakScore() > 6 && (player.getTieBreakScore() - otherPlayer.getTieBreakScore()) > 0) {
            player.incrementSetScore();
            player.setMatchWinner(true);
            player.setTieBreakScore(0);
            otherPlayer.setTieBreakScore(0);
            return true;
        }

        player.incrementTieBreakScore();
        return false;
    }

    private boolean checkSimpleSetRule(Player player, Player otherPlayer) {
        return ((player.getSetScore() == 5 && otherPlayer.getSetScore() < 5) || (player.getSetScore() == 6 && otherPlayer.getSetScore() < 6));
    }
}
