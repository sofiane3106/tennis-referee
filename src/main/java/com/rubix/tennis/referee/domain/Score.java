package com.rubix.tennis.referee.domain;

public interface Score {

    void markPoint(Player player);

    String getGameScore();
}
