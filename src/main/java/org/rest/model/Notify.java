package org.rest.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notify {
    @EmbeddedId
    private UserJobId id;

    @NonNull private String status;
}
