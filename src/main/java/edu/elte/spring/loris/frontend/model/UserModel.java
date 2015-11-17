package edu.elte.spring.loris.frontend.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import edu.elte.spring.loris.frontend.util.PasswordMatch;

@PasswordMatch
public class UserModel {
	
	@NotEmpty
	@Size(min = 4, max = 15, message="too short")
	private String username;
	@NotEmpty
	private String fullname;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Size(min = 6, max = 15, message="too short")
	private String password;
	@NotEmpty
	@Size(min = 6, max = 15, message="too short")
	private String passwordConfirm;
	
	public UserModel() {
	}

	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	
}
