package com.rubix.tennis.referee;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Player {

    @NotBlank
    @EqualsAndHashCode.Include
    private String firstName;

    @NotBlank
    @EqualsAndHashCode.Include
    private String lastName;

    private String score;

    public Player(String firstName, String lastName) {
        this(firstName, lastName, "0");
    }

    public Player(String firstName, String lastName, String score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

}
