package org.rest.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
	@Id
	@GeneratedValue
	private Long id;

	@NonNull private String title;

	@NonNull private String status;

	@NonNull private String description;

	@NonNull private String location;

	private double price;

	@NonNull private LocalDateTime date;

}
