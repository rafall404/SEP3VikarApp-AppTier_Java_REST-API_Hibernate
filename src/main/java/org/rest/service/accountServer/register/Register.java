package org.rest.service.accountServer.register;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.AccountDbHandler;
import org.rest.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.ZoneId;

@Path("account/")
public class Register {

    private AccountDbHandler accountDb;

    public Register() {

        accountDb = HandlersFactory.getInstance().getAccountDb();

    }


    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean register(RegisterDTO rDTO){

        System.out.println("Before wrapping into User "+rDTO.toString());
        if(accountDb.checkIfUsernameInUse(rDTO.getUsername())){
            return false;
        }
        if (!rDTO.getPassword().equals(rDTO.getRepeatedPassword())){
            return false;
        }
        LocalDate birthInLocalDate = rDTO.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (birthInLocalDate.isAfter(LocalDate.now().minusYears(18))){
            return false;
        }else {

            User newUser = new User(rDTO.getUsername(), rDTO.getPassword(), rDTO.getFirstName(), rDTO.getLastName(),
                    rDTO.getEmail(), rDTO.getPhone());
            System.out.println("After wrapping into User "+newUser.toString());
            accountDb.addNewAccount(newUser);
        }
        return true;

    }
}
