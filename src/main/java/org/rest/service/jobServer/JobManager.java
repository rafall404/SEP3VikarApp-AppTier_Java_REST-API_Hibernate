package org.rest.service.jobServer;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;
import org.rest.model.Job;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("job")
public class JobManager {

    private JobDbHandler jobDBHandler;

    public JobManager()
    {
        jobDBHandler = HandlersFactory.getInstance().getJobDb();
    }


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean createJob(JobDTO dto)
    {
        try {
            System.out.println("Job is about to be created with " + dto.getTitle());

            Job job = new Job();
            job.setTitle(dto.getTitle());
            job.setPrice(dto.getPrice());
            job.setDateTime(dto.getDateTime());
            job.setDescription(dto.getDescription());
            job.setLocation(dto.getLocation());
            job.setStatus("Available");
            jobDBHandler.createJob(job);
        }catch (NullPointerException e)
        {
            return false;
        }
        return true;

    }
}
