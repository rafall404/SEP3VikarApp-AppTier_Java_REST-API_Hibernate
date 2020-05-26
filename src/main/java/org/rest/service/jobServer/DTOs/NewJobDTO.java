package org.rest.service.jobServer.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@RequiredArgsConstructor
@Data
public class NewJobDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("location")
    private String location;

    @JsonProperty("price")
    private double price;

    @JsonProperty("dateTime")
    private String dateTime;

    @JsonProperty("username")
    private String username;

}