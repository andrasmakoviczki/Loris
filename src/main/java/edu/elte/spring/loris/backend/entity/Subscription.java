package edu.elte.spring.loris.backend.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Subscription implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7664300682700673603L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;
	@Column(name="LINK")
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CHANNEL_ID")
	private Channel channel;
	
	public Subscription() {
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subscription [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", channel=");
		builder.append(channel);
		builder.append("]");
		return builder.toString();
	}	
}
