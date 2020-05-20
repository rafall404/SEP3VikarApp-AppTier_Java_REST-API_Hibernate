package org.rest.service.accountServer.userjobs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long userId;

    @NonNull
    private String username;

    @NonNull private String password;

    @NonNull private String firstName;

    @NonNull private String lastName;

    @NonNull private String email;

    @NonNull private String phone;

    private int postedJobsNo;

    @NonNull private Date birthDate;

}
