package com.rubix.tennis.referee.process.impl;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.Score;
import com.rubix.tennis.referee.process.GameProcess;
import com.rubix.tennis.referee.service.GameService;
import com.rubix.tennis.referee.service.ScoreService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Named
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class GameProcessImpl implements GameProcess {

    private final ScoreService scoreService;
    private final GameService gameService;

    @Valid
    @Override
    public Game startNewGame(@Valid @NotNull Player playerOne, @Valid @NotNull Player playerTwo) {
        Game savedGame = gameService.startNewGame(playerOne, playerTwo);

        Score playerOneScore = scoreService.startNewGameScore(playerOne, savedGame);
        Score playerTwoScore = scoreService.startNewGameScore(playerTwo, savedGame);

        //savedGame.setScorePlayerOne(playerOneScore);
        //savedGame.setScorePlayerTwo(playerTwoScore);

        return gameService.save(savedGame);
    }
}
