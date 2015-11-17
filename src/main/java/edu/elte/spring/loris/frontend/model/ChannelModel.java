package edu.elte.spring.loris.frontend.model;

import java.util.Date;

import org.hibernate.validator.constraints.*;

public class ChannelModel   {
	
	@URL(message="not URL form")
	@NotBlank
	@NotEmpty
	private String link;

	public ChannelModel() {
	}

	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


}
