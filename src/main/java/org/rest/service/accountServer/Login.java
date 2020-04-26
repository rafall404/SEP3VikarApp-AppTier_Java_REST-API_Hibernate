package org.rest.service.accountServer;

import org.dbAccess.HandlersFactory;
import org.rest.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


    @Path("account/login/" )
    public class Login {

        private HandlersFactory factory;
        public Login() {
            factory= HandlersFactory.getInstance();
        }

       @POST
       @Consumes(MediaType.APPLICATION_JSON)
       public Boolean login(LoginDTO dto)
        {
            System.out.println("Username: " + dto.getUsername() + "Password : " + dto.getPassword());
            User u = new User();
            u.setUsername(dto.getUsername());
            u.setPassword(dto.getPassword());
           return factory.getLogInDb().checkIfExists(u);
        }

}
