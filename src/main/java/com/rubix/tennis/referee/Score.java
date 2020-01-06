package com.rubix.tennis.referee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Score {

    abstract void markPoint(Player player);

    protected void finishGame(Player player, Game game) {
        game.setOver(true);
        game.setWinner(player);
        log.info("{} has won the game", player.getFirstName());
        player.setScore("0");
    }
}
