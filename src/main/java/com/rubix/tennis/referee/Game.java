package com.rubix.tennis.referee;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@EqualsAndHashCode
@Setter
@Getter
@Builder
public class Game {

    @Valid
    private final Player playerOne;

    @Valid
    private final Player playerTwo;

    private boolean isOver;

    private Player winner;

    public String getScore() {
        StringBuilder gameScore = new StringBuilder();
        Player playerOne = getPlayerOne();
        Player playerTwo = getPlayerTwo();
        gameScore.append("Player : ").append(playerOne.toString()).append(" has score = ").append(playerOne.getScore()).append("\\n");
        gameScore.append("Player : ").append(playerTwo.toString()).append(" has score = ").append(playerTwo.getScore()).append("\\n");
        return gameScore.toString();
    }
}
