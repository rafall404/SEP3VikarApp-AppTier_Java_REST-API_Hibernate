package org.rest.service.accountServer.notify;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.AccountDbHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/notify")
public class Notify {
    private AccountDbHandler accountDbHandler;

    public Notify(AccountDbHandler accountDbHandler) {
        this.accountDbHandler = HandlersFactory.getInstance().getAccountDb();
    }

    @GET
    @Path("/getNotification")
    public List<GetNotificationsDTO> getNotifications(@QueryParam("userId") Long userId){
        return accountDbHandler.getUserNotifications(userId);
    }
}
