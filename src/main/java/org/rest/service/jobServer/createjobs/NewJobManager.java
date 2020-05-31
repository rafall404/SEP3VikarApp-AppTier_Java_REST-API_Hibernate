package org.rest.service.jobServer.createjobs;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;
import org.rest.model.Job;
import org.rest.service.jobServer.createjobs.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("job")
public class NewJobManager {

    private JobDbHandler jobDBHandler;

    public NewJobManager()
    {
        jobDBHandler = HandlersFactory.getInstance().getJobDb();
    }


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean createJob(NewJobDTO dto)
    {

        if(dto.getTitle()==null || dto.getDateTime()==null || dto.getLocation() ==null || dto.getPrice()<=0 || dto.getUsername()==null) {
           return false;
        }


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









}
