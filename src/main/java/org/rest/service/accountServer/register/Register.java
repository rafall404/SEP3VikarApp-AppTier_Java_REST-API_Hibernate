package org.rest.service.accountServer.register;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.RegisterDbHandler;
import org.rest.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("account/register/")
public class Register {

    private RegisterDbHandler registerDb;
    public Register() {
        registerDb = HandlersFactory.getInstance().getRegisterDb();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(RegisterDTO rDTO){


        System.out.println("Before wrapping into User "+rDTO.toString());

        User newUser = new User(rDTO.getUsername(), rDTO.getPassword(), rDTO.getFirstName(), rDTO.getLastName(),
                            rDTO.getEmail(), rDTO.getPhone());
        System.out.println("After wrapping into User "+newUser.toString());

        if (rDTO.getPassword().equals(rDTO.getRepeatedPassword())){
            Response.status(404);
        }

        registerDb.addNewAccount(newUser);


    }


}
