package org.rest.service.accountServer.login;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.LogInDbHandler;
import org.rest.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


    @Path("account/login/" )
    public class Login {

        private LogInDbHandler logInDb;
        public Login() {
            logInDb = HandlersFactory.getInstance().getLogInDb();
        }

       @POST
       @Consumes(MediaType.APPLICATION_JSON)
       public Boolean login(LoginDTO dto)
        {
            System.out.println("Username: " + dto.getUsername() + "Password : " + dto.getPassword());
            User u = new User();
            u.setUsername(dto.getUsername());
            u.setPassword(dto.getPassword());
           return logInDb.checkIfExists(u);
        }

}
