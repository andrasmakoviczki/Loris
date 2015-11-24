package edu.elte.spring.loris.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.util.UserException;

public interface FeedEntryService {
	public void removeFeedEntry(FeedEntry fe);
	public FeedEntry findFeedEntry(String id);
	public FeedEntry getLastFeedEntrybyChannel(Channel ch);
	public void updateFeedEntry(FeedEntry fe);
	public Set<FeedEntry> findFeedEntrybyDate(Date date);
	public Set<FeedEntry> findFeedEntrybyChannel(Channel ch);
	public List<Set<FeedEntry>> findFeedEntrybyUser() throws UserException;
	public Set<FeedEntry> listFeedEntry();
	public void createFeedEntry(FeedEntry fe);
	public Set<FeedEntry> findTopic(String feId,Integer topicNumber);
	public Set<FeedEntry> listbyChannelAfterRegistration(Channel ch, Date registrationDate);
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate,String term);
}
