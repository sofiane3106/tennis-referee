package com.rubix.tennis.referee.service;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.Score;

public interface ScoreService {

    Score startNewGameScore(Player player, Game game);
}
