package org.sep3.rest.accountServer;

import code.accountsManager.AccountManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


    @Path("account/login/" )
    public class Login {

        private AccountManager accountsManager;
        public Login()
        {
            accountsManager= new AccountManager();
        }

       @POST
       @Consumes(MediaType.APPLICATION_JSON)
       public Boolean login(LoginDTO dto)
        {
            System.out.println("Username: " + dto.getUsername() + "Password : " + dto.getPassword());
           return accountsManager.checkIfExists(dto.getUsername(),dto.getPassword());
        }





}
