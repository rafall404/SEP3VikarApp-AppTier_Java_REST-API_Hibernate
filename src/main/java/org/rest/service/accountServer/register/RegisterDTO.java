package org.rest.service.accountServer.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("birthDate")
    private Date birthDate;

    public RegisterDTO(){}

    public RegisterDTO(String userName, String password, String email, String fName, String lName, String phone) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.firstName = fName;
        this.lastName = lName;
        this.phone = phone;
    }
}
