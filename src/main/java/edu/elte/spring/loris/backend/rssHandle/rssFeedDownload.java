package edu.elte.spring.loris.backend.rssHandle;

import java.io.IOException;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public class rssFeedDownload {

	private URL feedUrl;
	private SyndFeed feed;

	public rssFeedDownload(String url) throws IllegalArgumentException, FeedException, IOException {
		// TODO: Url ellenőrzés
		this.feedUrl = new URL(url);
		SyndFeedInput input = new SyndFeedInput();
		this.feed = input.build(new XmlReader(feedUrl));
	}

	public void ChannelBuild(Channel ch) {
		ch.setLanguage(feed.getLanguage());
		ch.setPublishDate(feed.getPublishedDate());
		ch.setTitle(feed.getTitle());
	}

	// TODO: Itt már a leválogatott Channelek lesznek
	public FeedEntry FeedEntryBuild(SyndEntry f) {
		FeedEntry fe = new FeedEntry();
		SyndContentImpl sc = (SyndContentImpl) f. getContents().get(0);
		//SyndCategoryImpl  se = (SyndCategoryImpl) f.getCategories().get(0);
		
		fe.setTitle(f.getTitle());
		fe.setContent(sc.getValue());
		fe.setPublishDate(f.getPublishedDate());
		// Teljes lista
		//fe.setCategory(se.getName());
		
		return fe;
	}

	public URL getFeedUrl() {
		return feedUrl;
	}

	public SyndFeed getFeed() {
		return feed;
	}

	public void setFeedUrl(URL feedUrl) {
		this.feedUrl = feedUrl;
	}

	public void setFeed(SyndFeed feed) {
		this.feed = feed;
	}

}
