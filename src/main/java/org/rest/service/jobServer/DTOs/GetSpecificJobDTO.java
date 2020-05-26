package org.rest.service.jobServer.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.rest.model.Job;
import org.rest.model.User;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSpecificJobDTO {

   private Long jobId;

   @NonNull private String title;

   @NonNull private String status;

   @NonNull private String description;

   @NonNull private String location;

   private double price;

   @NonNull private LocalDateTime date;



   private Long userId;

   @NonNull
   private String username;

   @NonNull private String firstName;

   @NonNull private String lastName;

   @NonNull private String email;

   @NonNull private String phone;


}
