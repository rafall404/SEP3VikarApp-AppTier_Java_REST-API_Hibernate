package org.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
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



	public User(String username, String password, String fname, String lname, String emailAddress, String telephoneNumber) {
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.emailAddress = emailAddress;
		this.telephoneNumber = telephoneNumber;
	}

	@OneToMany
	private List<Job> postedJobs;

	public void removeJob(Job job){
		postedJobs.remove(job);
	}

	public void addJob(Job job){
		postedJobs.add(job);
	}
}
