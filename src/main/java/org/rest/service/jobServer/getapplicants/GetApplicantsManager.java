package org.rest.service.jobServer.getapplicants;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;
import org.rest.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("job")
public class GetApplicantsManager {

    private JobDbHandler jobDBHandler;

    public GetApplicantsManager()
    {
        jobDBHandler = HandlersFactory.getInstance().getJobDb();
    }

    @GET
    @Path("/getApplicants")
    public List<User> getApplicantsForJob(GetApplicantsDTO dto){
        return jobDBHandler.getApplicants(dto.getJobId());
    }
}
