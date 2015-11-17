package edu.elte.spring.loris.backend.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public class rssFeedDownload {

	private SyndFeed feed;

	public rssFeedDownload(URL url) throws IllegalArgumentException, FeedException, IOException {
		SyndFeedInput input = new SyndFeedInput();
		this.feed = input.build(new XmlReader(url));
	}

	public void ChannelBuild(Channel ch) {
		ch.setTitle(feed.getTitle());
		ch.setLanguage(feed.getLanguage());
		ch.setPublishDate(feed.getPublishedDate());
	}

	// TODO: Itt már a leválogatott Channelek lesznek
	public List<FeedEntry> FeedEntryBuild() {

		List<FeedEntry> feList = new ArrayList<>();
		for (Object c : feed.getEntries()) {
			SyndEntry content = (SyndEntryImpl) c;

			String feedEntryContent = Jsoup.parse(((SyndContentImpl) content.getContents().get(0)).getValue()).text();

			FeedEntry fe = new FeedEntry();
			fe.setTitle(content.getTitle());
			fe.setContent(feedEntryContent);
			fe.setPublishDate(content.getPublishedDate());
			fe.setLink(content.getLink());
			fe.setCategory(CategoryBuild(content.getCategories()));

			feList.add(fe);
		}

		return feList;
	}

	private List<Category> CategoryBuild(List<?> syndList) {
		List<Category> caList = new ArrayList<>();

		for (Object category : syndList) {
			Category ca = new Category();
			ca.setCategoryName(((SyndCategory) category).getName());
			caList.add(ca);
		}
		return caList;
	}

	public SyndFeed getFeed() {
		return feed;
	}

	public void setFeed(SyndFeed feed) {
		this.feed = feed;
	}

}
