package com.rubix.tennis.referee.service;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;

public interface GameService {

    Game startNewGame(Player playerOne, Player playerTwo);

    Game save(Game game);
}
