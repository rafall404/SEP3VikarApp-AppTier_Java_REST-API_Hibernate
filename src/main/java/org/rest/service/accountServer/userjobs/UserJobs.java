package org.rest.service.accountServer.userjobs;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.AccountDbHandler;
import org.rest.model.Job;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/account")
public class UserJobs {

    private AccountDbHandler accountDb;

    public UserJobs() {

        accountDb = HandlersFactory.getInstance().getAccountDb();

    }

    @GET
    @Path("/UserJobs")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Job> getAllUsersJobs(@QueryParam("idUser")Long id, @QueryParam("idJob")Long idJob )
    {

        System.out.println(id + "Id of sent user");
        return accountDb.getUserJobs(id, idJob);
    }


}
