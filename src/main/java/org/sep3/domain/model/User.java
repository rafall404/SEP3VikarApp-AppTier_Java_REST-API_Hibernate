package org.sep3.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

	private int id;

	private String username;

	private String password;

	private String fname;

	private String lname;

	private String emailAddress;

	private String telephoneNumber;


}
