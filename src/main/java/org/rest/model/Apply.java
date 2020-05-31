package org.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apply {

    @EmbeddedId
    private UserJobId id;

    boolean isAccepted;

    public Apply(UserJobId userJobId){
        id=userJobId;
        isAccepted= false;
    }
}
