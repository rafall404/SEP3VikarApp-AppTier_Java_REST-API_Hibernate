package org.rest.service.accountServer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterDTO {
    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String fName;

    @JsonProperty("lastName")
    private String lName;

    @JsonProperty("phone")
    private String phone;

    public RegisterDTO(){}

    public RegisterDTO(String userName, String password, String email, String fName, String lName, String phone) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }
}
