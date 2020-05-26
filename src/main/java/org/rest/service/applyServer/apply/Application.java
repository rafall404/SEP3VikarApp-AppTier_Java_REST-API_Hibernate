package org.rest.service.applyServer.apply;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.ApplyDbHandler;
import org.rest.service.applyServer.apply.ApplyDTO;

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
        return applyHandler.applyForJob(applyDTO.getUserId(), applyDTO.getJobId());
    }

}
