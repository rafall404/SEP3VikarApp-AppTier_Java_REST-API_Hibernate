package org.rest.service.accountServer.register;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.RegisterDbHandler;
import org.rest.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

        if (!rDTO.getPassword().equals(rDTO.getRepeatedPassword())){
            Response.status(400); // not sure it it works, next one too
        }
        LocalDate birthInLocalDate = rDTO.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (birthInLocalDate.isAfter(LocalDate.now().minusYears(18))){
            Response.status(400);
        }else {

            User newUser = new User(rDTO.getUsername(), rDTO.getPassword(), rDTO.getFirstName(), rDTO.getLastName(),
                    rDTO.getEmail(), rDTO.getPhone());
            System.out.println("After wrapping into User "+newUser.toString());
            registerDb.addNewAccount(newUser);
        }

    }


}
