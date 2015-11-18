package edu.elte.spring.loris.frontend.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

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
