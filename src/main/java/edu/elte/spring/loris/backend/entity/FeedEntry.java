package edu.elte.spring.loris.backend.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
// @Table(name = "FeedEntry", schema = "loris@hbase-pu")
public class FeedEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1047859559427007452L;
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
	@Column(name = "LABELED")
	private Boolean labeled;
	@Column(name = "LINK")
	private String link;
	@Column(name = "CHANNEL_ID")
	private String channelId;
	
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CHANNEL")
	private Channel channel;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "TOPIC")
	private List<Topic> topic;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY")
	private List<Category> category;

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

	public Date getPublishDate() {
		return publishDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Boolean getLabeled() {
		return labeled;
	}

	public Channel getChannel() {
		return channel;
	}

	public List<Topic> getTopic() {
		return topic;
	}

	public List<Category> getCategory() {
		return category;
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

	public void setLabeled(Boolean labeled) {
		this.labeled = labeled;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void setTopic(List<Topic> topic) {
		this.topic = topic;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
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
		builder.append(", labeled=");
		builder.append(labeled);
		builder.append(", link=");
		builder.append(link);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", topic=");
		builder.append(topic);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
}