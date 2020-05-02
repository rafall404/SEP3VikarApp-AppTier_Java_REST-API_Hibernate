package org.rest.service.accountServer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("username")
        private String username;

        @JsonProperty("password")
        private String password;

        @JsonProperty("repeatedPassword")
        private String repeatedPassword;

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

        public AccountDTO(){}

        public AccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
         }




}
