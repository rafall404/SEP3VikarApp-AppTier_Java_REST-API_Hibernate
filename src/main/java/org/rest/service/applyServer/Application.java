package org.rest.service.applyServer;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.ApplyDbHandler;
import org.rest.service.accountServer.AccountDTO;
import org.rest.service.jobServer.JobDTO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/apply")
public class Application {

    private ApplyDbHandler applyHandler;

    public Application(){
        applyHandler = HandlersFactory.getInstance().getApplyDb();
    }

    @POST
    @Path("/newApplication")
    public Boolean applyFor(ApplyDTO applyDTO){
        return applyHandler.applyForJob(applyDTO.getUserId(), applyDTO.getUserId());
    }
}
