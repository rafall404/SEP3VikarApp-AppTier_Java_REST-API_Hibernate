package org.rest.service.accountServer;

import org.dbAccess.HandlersFactory;
import org.dbAccess.dbHandlers.AccountDbHandler;
import org.rest.model.Job;
import org.rest.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Path("/account" )
public class Account {

    private AccountDbHandler accountDb;

    public Account() {

        accountDb = HandlersFactory.getInstance().getAccountDb();

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(AccountDTO dto)
    {
        System.out.println("Username: " + dto.getUsername() + "Password : " + dto.getPassword());
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setPassword(dto.getPassword());


        return accountDb.checkIfExists(u);

    }


    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean register(AccountDTO rDTO){

        System.out.println("Before wrapping into User "+rDTO.toString());

        if (!rDTO.getPassword().equals(rDTO.getRepeatedPassword())){
            return false; // not sure it it works, next one too
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

    @GET
    @Path("/UserJobs")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Job> getAllUsersJobs(@QueryParam("id")Long id)
    {

       System.out.println(id + "Id of sent user");
       return accountDb.getUserJobs(id);
    }



}
