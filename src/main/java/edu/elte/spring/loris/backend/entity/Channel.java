package edu.elte.spring.loris.backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "Channel", schema = "hbaseDB@hbase-pu")
public class Channel  {
	
	@Id
	@TableGenerator(name = "id_gen", allocationSize = 10, initialValue = 1)
    @GeneratedValue(generator = "id_gen", strategy = GenerationType.TABLE)
	private String id;
	@Column(name="title")
	private String title;
	 @Column(name="link")
	private String link;
	 @Column(name="language")
	private String language;
	@Column(name="publishDate")
	private Date publishDate;

	public Channel() {
	}
	
	public Channel(String title, String link, String language, Date publishDate) {
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
		builder.append("]");
		return builder.toString();
	}
}
