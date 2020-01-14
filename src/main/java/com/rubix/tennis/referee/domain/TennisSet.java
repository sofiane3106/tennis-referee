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
public class TennisSet extends AbstractMatch {

    private List<TennisGame> tennisGames;

    @Builder
    public TennisSet(Player playerOne, Player playerTwo, ScoreRule scoreRule) {
        super(playerOne, playerTwo, scoreRule);
        tennisGames = new ArrayList<>();
    }

    private void finish(Player winner) {
        setOver(true);
        setWinner(winner);

        if(tieBreakIsActivated(playerOne, playerTwo)){
            winner.incrementSetScore();
            winner.setMatchWinner(true);
            playerOne.setTieBreakScore(0);
            playerTwo.setTieBreakScore(0);
        }

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

    public TennisGame startNewGame() {
        ScoreRule scoreGame = new ScoreRuleGameRule2();
        TennisGame tennisGame = TennisGame.builder().playerOne(playerOne).playerTwo(playerTwo).scoreRule(scoreGame).build();
        tennisGame.setTennisSet(this);
        this.getTennisGames().add(tennisGame);
        return tennisGame;
    }

    @Override
    public String getScore() {
        StringBuilder setScore = new StringBuilder();
        Player playerOne = getPlayerOne();
        Player playerTwo = getPlayerTwo();
        setScore.append("Player : ").append(playerOne.toString()).append(" has setScore = ").append(playerOne.getSetScore()).append("\n");
        setScore.append("Player : ").append(playerTwo.toString()).append(" has setScore = ").append(playerTwo.getSetScore());
        return setScore.toString();
    }

    // TODO: to mutualise with the same method exist in ScoreRuleSetRule2
    private boolean tieBreakIsActivated(Player player, Player otherPlayer) {
        return player.getSetScore() == 6 && otherPlayer.getSetScore() == 6;
    }
}
