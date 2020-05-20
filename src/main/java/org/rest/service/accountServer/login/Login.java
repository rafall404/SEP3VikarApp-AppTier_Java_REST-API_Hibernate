package org.rest.service.accountServer.login;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.AccountDbHandler;
import org.rest.service.accountServer.userjobs.AccountDTO;
import org.rest.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("account/")
public class Login {
    private AccountDbHandler accountDb;

    public Login() {

        accountDb = HandlersFactory.getInstance().getAccountDb();

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean login(LoginDTO dto)
    {
        System.out.println("Username: " + dto.getUsername() + "Password : " + dto.getPassword());
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setPassword(dto.getPassword());


        return accountDb.checkIfExists(u);

    }

    @POST
    @Path("/getAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public AccountDTO getAccount(LoginDTO dto)
    {
        System.out.println("Username: " + dto.getUsername() + "Password : " + dto.getPassword());
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setPassword(dto.getPassword());


        return accountDb.getUser(u);
    }

}
