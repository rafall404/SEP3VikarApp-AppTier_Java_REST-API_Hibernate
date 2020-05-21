package org.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
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
