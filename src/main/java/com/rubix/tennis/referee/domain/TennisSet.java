package com.rubix.tennis.referee.domain;

import com.rubix.tennis.referee.rules.ScoreRule;
import com.rubix.tennis.referee.rules.ScoreRuleGameRule2;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
public class TennisSet extends Match {

    private List<Game> games;

    @Builder
    public TennisSet(Player playerOne, Player playerTwo, ScoreRule scoreRule) {
        super(playerOne, playerTwo, scoreRule);
        games = new ArrayList<>();
    }

    private void finish(Player winner) {
        setOver(true);
        setWinner(winner);
        log.info("{} has won the set ", winner.getFirstName());
        if (!winner.isMatchWinner()) {
            playerOne.setSetScore(0);
            playerTwo.setSetScore(0);
        }
    }

    void markPoint(Player player) {
        if (isOver()) {
            throw new RuntimeException("Set already over");
        }

        Player otherPlayer = getOtherPlayer(player);

        if (scoreRule.hasWon(player, otherPlayer)) {
            finish(player);
        } else {
            scoreRule.incrementPlayerScore(player, otherPlayer);
        }
    }

    public Game startNewGame() {
        ScoreRule scoreGame = new ScoreRuleGameRule2();
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreGame).build();
        game.setTennisSet(this);
        this.getGames().add(game);
        return game;
    }

    @Override
    String getScore() {
        StringBuilder setScore = new StringBuilder();
        Player playerOne = getPlayerOne();
        Player playerTwo = getPlayerTwo();
        setScore.append("Player : ").append(playerOne.toString()).append(" has gameScore = ").append(playerOne.getSetScore()).append("\\n");
        setScore.append("Player : ").append(playerTwo.toString()).append(" has gameScore = ").append(playerTwo.getSetScore()).append("\\n");
        return setScore.toString();
    }
}
