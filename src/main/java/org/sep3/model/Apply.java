package org.sep3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Apply {

    @EmbeddedId
    private UserJobId id;

    boolean isAccepted;

    public Apply(Long user, Long job, boolean accepted) {
        id = new UserJobId(user, job);
        isAccepted = accepted;
    }
}
