package org.rest.service.jobServer;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;
import org.rest.model.Job;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public Boolean createJob(JobDTO dto)
    {
        if(dto.getTitle()==null || dto.getDateTime()==null || dto.getLocation() ==null || dto.getPrice()<=0 || dto.getUsername()==null) {
           return false;
        }

            System.out.println("Job is about to be created with " + dto.getTitle());
            System.out.println("###############################" + dto.getUsername());

            Job job = new Job();
            job.setTitle(dto.getTitle());
            job.setPrice(dto.getPrice());
            job.setDateTime(dto.getDateTime());
            job.setDescription(dto.getDescription());
            job.setLocation(dto.getLocation());
            job.setStatus("Available");
            jobDBHandler.createJob(job,dto.getUsername());

        return true;

    }

    @GET
    @Path("/getJobs")
    public List<Job> getJobs(@QueryParam("location") String location, @QueryParam("id") Long id){
        return jobDBHandler.findJobs(location,id);
    }



}
