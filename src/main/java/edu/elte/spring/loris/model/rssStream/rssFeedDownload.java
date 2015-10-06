package edu.elte.spring.loris.model.rssStream;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class rssFeedDownload {

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

	private URL feedUrl;
	private SyndFeed feed;

	public rssFeedDownload(String url) {
		try {
			// URL feedUrl = new URL("http://index.hu/24ora/rss");
			this.feedUrl = new URL(url);
			SyndFeedInput input = new SyndFeedInput();
			// SyndFeed feed = input.build(new
			// InputStreamReader(feedUrl.openStream()));
			this.feed = input.build(new XmlReader(feedUrl));

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
	}

	//private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	public void fetcher() {
		
		/*try {
			//Class.forName(driverName);

			Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000", "hive", "");
			Statement stmt = con.createStatement();
			String tableName = "testHiveDriverTable";
			stmt.executeQuery("drop table " + tableName);
			ResultSet res = stmt.executeQuery("create table " + tableName + " (key int, value string)");
			// show tables
			String sql = "show tables '" + tableName + "'";
			System.out.println("Running: " + sql);
			res = stmt.executeQuery(sql);
			if (res.next()) {
				System.out.println(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.exit(1);
		}*/
	}
}
