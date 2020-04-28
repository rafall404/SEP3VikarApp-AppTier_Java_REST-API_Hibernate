package org.rest.service.accountServer.register;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.RegisterDbHandler;
import org.rest.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        registerDb.addNewAccount(newUser);
    }


}
