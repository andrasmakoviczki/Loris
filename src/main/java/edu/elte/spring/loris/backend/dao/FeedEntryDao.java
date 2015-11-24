package edu.elte.spring.loris.backend.dao;

import java.util.Date;
import java.util.Set;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManager;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public interface FeedEntryDao{
	public FeedEntry findFeedEntry(String id);
	public FeedEntry selectLastFeedEntryByChannel(Channel ch);
	public Set<FeedEntry> findFeedEntrybyDate(Date date);
	public Set<FeedEntry> findFeedEntrybyChannel(Channel ch);
	public Set<FeedEntry> listFeedEntry();
	public Set<FeedEntry> listbyChannelAfterRegistration(Channel ch,Date registrationDate);
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch,Date registrationDate,String term);
}