package com.rubix.tennis.referee.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AbstractBaseEntity implements Serializable, Comparable<AbstractBaseEntity> {

    @Getter
    private final long id;

    private final OffsetDateTime creationDate;

    protected AbstractBaseEntity() {
        this.creationDate = OffsetDateTime.now();
        id = 0;
    }

    @Override
    public int compareTo(AbstractBaseEntity other) {
        if (this.id == other.id) {
            return 0;
        }

        return this.getCreationDate().compareTo(other.getCreationDate());
    }

}
