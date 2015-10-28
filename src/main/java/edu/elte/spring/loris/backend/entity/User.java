package edu.elte.spring.loris.backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="IS_ENABLE")
	private boolean isEnable;
	
	@Column(name="LAST_LOGIN")
	private Date lastLogin;	
	
	@Column(name="CREATE_DATE")
	private Date createDate;	    
    
    public User(){}

	public User(String id, String name, String email, String password, boolean isEnable, Date lastLogin,
			Date createDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isEnable = isEnable;
		this.lastLogin = lastLogin;
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", isEnable=");
		builder.append(isEnable);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}    
}