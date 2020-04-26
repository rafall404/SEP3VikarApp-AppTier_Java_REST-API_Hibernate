package org.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="\"user\"")
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@NonNull private String username;

	@NonNull private String password;

	@NonNull private String fname;

	@NonNull private String lname;

	@NonNull private String emailAddress;

	@NonNull private String telephoneNumber;

	@OneToMany
	private List<Job> postedJobs;

	public void removeJob(Job job){
		postedJobs.remove(job);
	}

	public void addJob(Job job){
		postedJobs.add(job);
	}
}
