package edu.elte.spring.loris.frontend.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ChannelModel   {
	
	private String title;
	@URL(message="not URL form")
	@NotBlank
	private String link;
	private String language;
	private Date publishDate;

	public ChannelModel() {
	}
	
	public ChannelModel(String title, String link, String language, Date publishDate) {
		this.title = title;
		this.link = link;
		this.language = language;
		this.publishDate = publishDate;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getLanguage() {
		return language;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelModel [title=");
		builder.append(title);
		builder.append(", link=");
		builder.append(link);
		builder.append(", language=");
		builder.append(language);
		builder.append(", publishDate=");
		builder.append(publishDate);
		builder.append("]");
		return builder.toString();
	}


}
