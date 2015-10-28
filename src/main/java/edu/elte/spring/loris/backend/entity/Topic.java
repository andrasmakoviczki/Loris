package edu.elte.spring.loris.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "TOPIC_NAME")
	private String topicName;

	@Column(name = "TOPIC_VALUE")
	private String topicValue;

	public Topic() {
	}

	public Topic(String id, String topicName, String topicValue) {
		this.id = id;
		this.topicName = topicName;
		this.topicValue = topicValue;
	}

	public String getId() {
		return id;
	}

	public String getTopicName() {
		return topicName;
	}

	public String getTopicValue() {
		return topicValue;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public void setTopicValue(String topicValue) {
		this.topicValue = topicValue;
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
		builder.append("]");
		return builder.toString();
	}
}
