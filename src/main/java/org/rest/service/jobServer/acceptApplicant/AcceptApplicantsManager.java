package org.rest.service.jobServer.acceptApplicant;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.JobDbHandler;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("job")
public class AcceptApplicantsManager {
    private JobDbHandler jobDBHandler;

    public AcceptApplicantsManager()
    {
        jobDBHandler = HandlersFactory.getInstance().getJobDb();
    }

    @POST
    @Path("/acceptApplicants")
    public void acceptApplicants(AcceptApplicantsDTO dto){
        jobDBHandler.acceptApplicants(dto.getJobId(), dto.getUsersId());
    }
}
