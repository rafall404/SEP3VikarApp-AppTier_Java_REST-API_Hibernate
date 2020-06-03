package org.rest.service.accountServer.notify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNotificationsDTO {
    private Long jobId;
    private String titleJob;
    private String status;
}
