package edu.elte.spring.loris.backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic implements Serializable {

	private static final long serialVersionUID = -7625389095191812618L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;

	@Column(name = "TOPIC_NAME")
	private String topicName;
	@Column(name = "TOPIC_VALUE")
	private Double topicValue;
	@Column(name = "FEEDENTRY_ID")
	private String feedEntryId;

	public Topic() {
	}

	public String getId() {
		return id;
	}

	public String getTopicName() {
		return topicName;
	}

	public Double getTopicValue() {
		return topicValue;
	}

	public String getFeedEntry() {
		return feedEntryId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public void setTopicValue(Double topicValue) {
		this.topicValue = topicValue;
	}

	public void setFeedEntry(String feedEntryId) {
		this.feedEntryId = feedEntryId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Topic [id=");
		builder.append(id);
		builder.append(", topicName=");
		builder.append(topicName);
		builder.append(", topicValue=");
		builder.append(topicValue);
		builder.append(", feedEntryId=");
		builder.append(feedEntryId);
		builder.append("]");
		return builder.toString();
	}
}
