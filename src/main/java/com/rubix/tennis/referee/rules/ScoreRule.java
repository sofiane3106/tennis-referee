package com.rubix.tennis.referee.rules;

import com.rubix.tennis.referee.domain.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ScoreRule {

    public abstract boolean hasWon(Player player, Player otherPlayer);

    public abstract void incrementPlayerScore(Player player, Player otherPlayer);
}
