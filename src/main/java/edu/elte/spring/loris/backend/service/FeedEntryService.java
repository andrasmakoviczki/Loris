package edu.elte.spring.loris.backend.service;

import java.util.Date;
import java.util.List;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.util.UserException;

public interface FeedEntryService {
	public void removeFeedEntry();
	public FeedEntry findFeedEntry(String id);
	public FeedEntry getLastFeedEntry();
	public void updateFeedEntry(FeedEntry fe);
	public List<FeedEntry> findFeedEntrybyDate(Date date);
	public List<FeedEntry> findFeedEntrybyChannel(Channel ch);
	public List<FeedEntry> findFeedEntrybyUser() throws UserException;
	public List<FeedEntry> listFeedEntry();
	public List<FeedEntry> listFeedEntry2(String a);
	public void createFeedEntry(FeedEntry fe);
	public List<FeedEntry> findTopic(String feId,Integer topicNumber);
}
