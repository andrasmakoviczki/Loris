package edu.elte.spring.loris.backend.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{

	private static final long serialVersionUID = -4526623595253624186L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	@Column(name = "FEEDENTRY_ID")
	private String feedEntryId;

	@OneToMany(cascade =  CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name = "ID")
	private List<FeedEntry> feedEntry;

	public Category() {
	}

	public String getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=");
		builder.append(id);
		builder.append(", categoryName=");
		builder.append(categoryName);
		builder.append(", feedEntryId=");
		builder.append(feedEntryId);
		builder.append(", feedEntry=");
		builder.append(feedEntry);
		builder.append("]");
		return builder.toString();
	}

	public String getFeedEntryId() {
		return feedEntryId;
	}

	public void setFeedEntryId(String feedEntryId) {
		this.feedEntryId = feedEntryId;
	}

	public List<FeedEntry> getFeedEntry() {
		return feedEntry;
	}

	public void setFeedEntry(List<FeedEntry> feedEntry) {
		this.feedEntry = feedEntry;
	}
}
