package com.rubix.tennis.referee.process;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;

public interface GameProcess {

    Game startNewGame(Player playerOne, Player playerTwo);
}
