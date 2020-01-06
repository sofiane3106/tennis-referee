package com.rubix.tennis.referee.service.impl;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.service.GameService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Named
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class GameServiceImpl implements GameService {

    @Valid
    @Override
    public Game startNewGame(@Valid @NotNull Player playerOne, @Valid @NotNull Player playerTwo) {
        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();
        return this.save(game);
    }

    @Override
    public Game save(Game game) {
        return game;
    }

    public void markPoint(Player player, Game game) {

        if (game.isOver()) {
            throw new RuntimeException("This game is already over");
        }
/*
        int scoreGamePoint = score.getGamePoint();

        String gamePoint = scorePoints.get(score.getGamePoint());

        if(gameHasWinner(gamePoint)){

        }

        score.setGamePoint(++scoreGamePoint);
        scoreRepository.save(score);

        String gamePoint = scorePoints.get(score.getGamePoint());
        isWinner(gamePoint);
    */
    }
}
