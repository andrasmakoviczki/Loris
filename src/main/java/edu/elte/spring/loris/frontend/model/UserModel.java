package edu.elte.spring.loris.frontend.model;

public class UserModel {
	
	private String username;
	private String fullname;
	private String email;
	private String password;
	
	public UserModel() {
	}
	
	public UserModel(String username, String fullname, String email, String password) {
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	public String getusername() {
		return username;
	}

	public String getfullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public void setfullname(String fullname) {
		this.fullname = fullname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserModel [username=");
		builder.append(username);
		builder.append(", fullname=");
		builder.append(fullname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}	
}
