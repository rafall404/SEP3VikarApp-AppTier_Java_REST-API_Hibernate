package org.rest.service.accountServer;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.RegisterDbHandler;
import org.rest.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
@Path("account/register/")
public class Register {

    private RegisterDbHandler registerDb;
    public Register() {
        registerDb = (RegisterDbHandler) HandlersFactory.getInstance().getRegisterDb();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(RegisterDTO rDTO){
        User newUser = new User(rDTO.getUserName(), rDTO.getPassword(), rDTO.getFName(), rDTO.getLName(),
                            rDTO.getEmail(), rDTO.getPhone());

        registerDb.addNewAccount(newUser);
    }


}
