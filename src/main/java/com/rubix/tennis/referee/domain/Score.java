package com.rubix.tennis.referee.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Score extends AbstractBaseEntity {

    private final Player player;

    private final Game game;

    private int gamePoint;

    protected Score(Player player, Game game) {
        this.player = player;
        this.game = game;
        this.gamePoint = 0;
    }

    protected abstract void markPoint();
}
