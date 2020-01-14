package com.rubix.tennis.referee.rules;

import com.rubix.tennis.referee.domain.Player;

public interface ScoreRule {

    boolean hasWon(Player player, Player otherPlayer);

    void incrementPlayerScore(Player player, Player otherPlayer);
}
