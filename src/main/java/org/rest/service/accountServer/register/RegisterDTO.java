package org.rest.service.accountServer.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NonNull
    private String username;

    @NonNull private String password;

    @NonNull private String firstName;

    @NonNull private String lastName;

    @NonNull private String email;

    @NonNull private String phone;

    @NonNull private String repeatedPassword;

    @NonNull private Date birthDate;

}
