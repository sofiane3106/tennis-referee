package com.rubix.tennis.referee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
public class Player {

    @NotBlank
    @EqualsAndHashCode.Include
    private String firstName;

    @NotBlank
    @EqualsAndHashCode.Include
    private String lastName;

    private String gameScore;

    private int setScore;

    private int tieBreakScore;

    //TODO: to be refactored
    private boolean isMatchWinner;

    public Player(String firstName, String lastName) {
        this(firstName, lastName, "0");
    }

    public Player(String firstName, String lastName, String gameScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gameScore = gameScore;
    }

    public Player(String firstName, String lastName, String gameScore, int setScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gameScore = gameScore;
        this.setScore = setScore;
    }

    public void incrementSetScore() {
        setSetScore(++setScore);
    }

    public void incrementTieBreakScore() {
        setTieBreakScore(++tieBreakScore);
    }
}
