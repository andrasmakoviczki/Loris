package edu.elte.spring.loris.backend.dao;

import java.util.Date;
import java.util.List;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public interface FeedEntryDao {
	public void insertFeedEntry(FeedEntry fe);
	public void updateFeedEntry(FeedEntry fe);
	public FeedEntry findFeedEntry(String id);
	public FeedEntry selectLastFeedEntry();
	public List<FeedEntry> findFeedEntrybyDate(Date date);
	public List<FeedEntry> findFeedEntrybyChannel(Channel ch);
	public List<FeedEntry> listFeedEntry();
	public List<FeedEntry> listFeedEntry2(String a);
	void removeFeedEntry(FeedEntry fe);
}
