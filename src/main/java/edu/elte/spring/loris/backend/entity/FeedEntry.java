package edu.elte.spring.loris.backend.entity;

import java.util.Date;
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
//import javax.persistence.Table;
//import javax.persistence.TableGenerator;

@Entity
// @Table(name = "FeedEntry", schema = "loris@hbase-pu")
public class FeedEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "PUBLISH_DATE")
	private Date publishDate;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "TOPIC")
	private List<Topic> topic;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER")
	private List<User> user;

	public FeedEntry() {
	}

	public FeedEntry(String id, String content, String title, Date publishDate, Date createDate) {
		this.id = id;
		this.content = content;
		this.title = title;
		this.publishDate = publishDate;
		this.createDate = createDate;
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

	public Date getPublishDate() {
		return publishDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public List<Topic> getTopic() {
		return topic;
	}

	public List<User> getUser() {
		return user;
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

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setTopic(List<Topic> topic) {
		this.topic = topic;
	}

	public void setUser(List<User> user) {
		this.user = user;
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
		builder.append(", publishDate=");
		builder.append(publishDate);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", topic=");
		builder.append(topic);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
}
