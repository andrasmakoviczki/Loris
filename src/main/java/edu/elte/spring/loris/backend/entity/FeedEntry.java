package edu.elte.spring.loris.backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeedEntry {

	@Id
	@Column(name="id")
	private String id;
	@Column(name="content")
	private String content;
	@Column(name="title")
	private String title;
	@Column(name="category")
	private String category;
	@Column(name="publised_date")
	private	Date   publihedDate;
	
	public FeedEntry(String id, String content, String title, String category, Date publihedDate) {
		this.id = id;
		this.content = content;
		this.title = title;
		this.category = category;
		this.publihedDate = publihedDate;
	}

	public FeedEntry() {
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public Date getPublihedDate() {
		return publihedDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPublihedDate(Date publihedDate) {
		this.publihedDate = publihedDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FeedEntry [id=");
		builder.append(id);
		builder.append(", content=");
		builder.append(content);
		builder.append(", title=");
		builder.append(title);
		builder.append(", category=");
		builder.append(category);
		builder.append(", publihedDate=");
		builder.append(publihedDate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
