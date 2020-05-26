package org.rest.service.jobServer;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;
import org.rest.model.Job;
import org.rest.model.User;
import org.rest.service.jobServer.DTOs.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public Boolean createJob(NewJobDTO dto)
    {
        System.out.println("###############################" + dto.getUsername());
        System.out.println("###############################" + dto.getDateTime());
        System.out.println("###############################" + dto.getLocation());
        System.out.println("###############################" + dto.getPrice());
        System.out.println("###############################" + dto.getTitle());
        if(dto.getTitle()==null || dto.getDateTime()==null || dto.getLocation() ==null || dto.getPrice()<=0 || dto.getUsername()==null) {
           return false;
        }

            System.out.println("Job is about to be created with " + dto.getTitle());
            System.out.println("###############################" + dto.getUsername());

            Job job = new Job();
            job.setTitle(dto.getTitle());
            job.setPrice(dto.getPrice());
            String date = dto.getDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(date,formatter);
            job.setDate(localDateTime);
            job.setDescription(dto.getDescription());
            job.setLocation(dto.getLocation());
            job.setStatus("open");
            jobDBHandler.createJob(job,dto.getUsername());

        return true;

    }

    @GET
    @Path("/getJobs")
    public SearchJobsDTO getJobs(@QueryParam("location") String location, @QueryParam("id") Long nextId){
        return jobDBHandler.findJobs(location,nextId);
    }

    @GET
    @Path("/getJobAndPoster")
    public GetSpecificJobDTO getSpecificJob(@QueryParam("jobId") Long jobId)
    {
        System.out.println(jobDBHandler.getSpecificjobAndUserInfo(jobId));
        return jobDBHandler.getSpecificjobAndUserInfo(jobId);

    }

    @GET
    @Path("/getApplicants")
    public List<User> getApplicantsForJob(GetApplicantsDTO dto){
        return jobDBHandler.getApplicants(dto.getJobId());
    }

    @POST
    @Path("/acceptApplicants")
    public void acceptApplicants(AcceptApplicantsDTO dto){
        jobDBHandler.acceptApplicants(dto.getJobId(), dto.getUsersId());
    }

}
