package org.rest.service.jobServer.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class AcceptApplicantsDTO {
    private List<Long> usersId;

    private Long JobId;
}
