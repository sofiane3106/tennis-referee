package com.rubix.tennis.referee.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Builder
public class Game extends AbstractBaseEntity {

    @Valid
    private final Player playerOne;

    @Valid
    private final Player playerTwo;

    private boolean isOver;
}
