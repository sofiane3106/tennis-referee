package com.rubix.tennis.referee.rules;

import com.rubix.tennis.referee.domain.Player;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ScoreRuleSetRule1 extends ScoreRule {

    @Override
    public void incrementPlayerScore(Player player, Player otherPlayer) {
        player.incrementSetScore();
    }

    @Override
    public boolean hasWon(Player player, Player otherPlayer) {
        return ((player.getSetScore() == 5 && otherPlayer.getSetScore() < 5) || player.getSetScore() == 6);
    }
}
