package edu.elte.spring.loris.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
public class Channel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -67196401224889676L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	@Column(name="TITLE")
	private String title;
	@Column(name="LINK")
	private String link;
	@Column(name="LANGUAGE")
	private String language;
	@Column(name="PUBLISH_DATE")
	private Date publishDate;
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;
	@Column(name="CREATE_DATE")
	private Date createDate;

	public Channel() {
	}

	public String getId() {
		return id;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Channel [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", link=");
		builder.append(link);
		builder.append(", language=");
		builder.append(language);
		builder.append(", publishDate=");
		builder.append(publishDate);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
}
