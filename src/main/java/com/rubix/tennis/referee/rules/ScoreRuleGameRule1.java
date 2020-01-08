package com.rubix.tennis.referee.rules;

import com.rubix.tennis.referee.domain.Player;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
public class ScoreRuleGameRule1 extends ScoreRule {

    public static final List<String> scorePoints = Arrays.asList("0", "15", "30", "40");

    @Override
    public void incrementPlayerScore(Player player, Player otherPlayer) {
        int i = scorePoints.indexOf(player.getGameScore());
        String newScore = scorePoints.get(++i);
        player.setGameScore(newScore);
    }

    @Override
    public boolean hasWon(Player player, Player otherPlayer) {
        return player.getGameScore().equals(scorePoints.get(scorePoints.size() - 1));
    }
}
