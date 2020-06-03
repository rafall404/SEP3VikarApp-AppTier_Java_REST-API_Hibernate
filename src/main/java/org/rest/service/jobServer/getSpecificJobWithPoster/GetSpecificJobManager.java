package org.rest.service.jobServer.getSpecificJobWithPoster;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("job")
public class GetSpecificJobManager {

    private JobDbHandler jobDBHandler;

    public GetSpecificJobManager()
    {
        jobDBHandler = HandlersFactory.getInstance().getJobDb();
    }


    @GET
    @Path("/getJobAndPoster")
    public GetSpecificJobDTO getSpecificJob(@QueryParam("jobId") Long jobId)
    {
        System.out.println(jobDBHandler.getSpecificjobAndUserInfo(jobId));
        return jobDBHandler.getSpecificjobAndUserInfo(jobId);

    }
}
