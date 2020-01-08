package com.rubix.tennis.referee.rules;

import com.rubix.tennis.referee.domain.Player;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
public class ScoreRuleGameRule2 extends ScoreRule {

    public static final List<String> scorePoints = Arrays.asList("0", "15", "30", "40");
    public static final String ADVANTAGE_POINT = "ADV";
    public static final String DEUCE_POINT = "DEUCE";

    @Override
    public void incrementPlayerScore(Player player, Player otherPlayer) {
        String lastScore = scorePoints.get(scorePoints.size() - 1);

        String scorePlayer;
        String scoreOtherPlayer;

        if (player.getGameScore().equals(DEUCE_POINT) && otherPlayer.getGameScore().equals(DEUCE_POINT)) {
            scorePlayer = ADVANTAGE_POINT;
            scoreOtherPlayer = lastScore;
        } else if (player.getGameScore().equals(lastScore) && otherPlayer.getGameScore().equals(ADVANTAGE_POINT)) {
            scorePlayer = DEUCE_POINT;
            scoreOtherPlayer = DEUCE_POINT;
        } else if (player.getGameScore().equals(lastScore)) { // we don't need to test other player here as it was tested in hasWon() method
            scorePlayer = ADVANTAGE_POINT;
            scoreOtherPlayer = otherPlayer.getGameScore();
        } else {
            int i = scorePoints.indexOf(player.getGameScore());
            scorePlayer = scorePoints.get(++i);
            scoreOtherPlayer = otherPlayer.getGameScore();
        }

        player.setGameScore(scorePlayer);
        otherPlayer.setGameScore(scoreOtherPlayer);
    }

    @Override
    public boolean hasWon(Player player, Player otherPlayer) {
        String lastScore = scorePoints.get(scorePoints.size() - 1);

        return player.getGameScore().equals(ADVANTAGE_POINT) ||
                (player.getGameScore().equals(lastScore) && otherPlayer.getGameScore().compareTo(lastScore) < 0);
    }
}
