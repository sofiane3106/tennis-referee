package com.rubix.tennis.referee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class Player extends AbstractBaseEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String score;
}
