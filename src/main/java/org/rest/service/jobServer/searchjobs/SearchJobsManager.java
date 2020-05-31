package org.rest.service.jobServer.searchjobs;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("job")
public class SearchJobsManager {
    private JobDbHandler jobDBHandler;

    public SearchJobsManager()
    {
        jobDBHandler = HandlersFactory.getInstance().getJobDb();
    }

    @GET
    @Path("/getJobs")
    public SearchJobsDTO getJobs(@QueryParam("location") String location, @QueryParam("id") Long nextId){
        return jobDBHandler.findJobs(location,nextId);
    }
}
