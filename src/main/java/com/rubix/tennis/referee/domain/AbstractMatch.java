package com.rubix.tennis.referee.domain;

import com.rubix.tennis.referee.rules.ScoreRule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
abstract class AbstractMatch {

    @Valid
    protected final Player playerOne;

    @Valid
    protected final Player playerTwo;

    @NotNull
    protected final ScoreRule scoreRule;

    private boolean isOver;

    private Player winner;

    Player getOtherPlayer(Player player) {
        return getPlayerOne().equals(player) ? getPlayerTwo() : getPlayerOne();
    }

    abstract String getScore();

}
