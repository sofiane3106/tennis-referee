package com.rubix.tennis.referee.service.impl;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import com.rubix.tennis.referee.domain.Score;
import com.rubix.tennis.referee.domain.ScoreRule1;
import com.rubix.tennis.referee.service.ScoreService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Named
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class ScoreServiceImpl implements ScoreService {

    public static final List<String> scorePoints = Arrays.asList("0", "15", "30", "40");

    @Valid
    @Override
    public Score startNewGameScore(@Valid @NotNull Player player, @Valid @NotNull Game game) {
        Score scorePlayer = ScoreRule1.builder().player(player).game(game).build();
        return scorePlayer;
    }

}
