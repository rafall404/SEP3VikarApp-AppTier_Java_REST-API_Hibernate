package org.rest.service.jobServer.searchjobs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rest.model.Job;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchJobsDTO {
    private List<Job> jobs;
    private int numberOfJobs;
}
