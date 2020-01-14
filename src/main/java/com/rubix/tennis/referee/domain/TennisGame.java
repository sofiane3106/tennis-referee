package com.rubix.tennis.referee.domain;

import com.rubix.tennis.referee.rules.ScoreRule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Getter
@Setter
@Slf4j
public class TennisGame extends AbstractMatch {

    private TennisSet tennisSet;

    @Builder
    public TennisGame(Player playerOne, Player playerTwo, ScoreRule scoreRule) {
        super(playerOne, playerTwo, scoreRule);
    }

    @Override
    public String getScore() {
        StringBuilder gameScore = new StringBuilder();
        Player playerOne = getPlayerOne();
        Player playerTwo = getPlayerTwo();
        gameScore.append("Player : ").append(playerOne.toString()).append(" has gameScore = ").append(playerOne.getGameScore()).append("\n");
        gameScore.append("Player : ").append(playerTwo.toString()).append(" has gameScore = ").append(playerTwo.getGameScore());
        return gameScore.toString();
    }

    private void finish(Player player) {
        setOver(true);
        setWinner(player);

        player.setGameScore("0");
        Player loser = getOtherPlayer(player);
        loser.setGameScore("0");

        if (!Objects.isNull(tennisSet)) {
            tennisSet.markPoint(player);
        }
    }

    public void markPoint(Player player) {
        if (isOver()) {
            throw new RuntimeException("TennisGame already over");
        }

        Player otherPlayer = getOtherPlayer(player);

        if (scoreRule.hasWon(player, otherPlayer)) {
            finish(player);
        } else {
            scoreRule.incrementPlayerScore(player, otherPlayer);
        }
    }
}
