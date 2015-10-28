package edu.elte.spring.loris.backend.service;

import java.util.Date;
import java.util.List;

import edu.elte.spring.loris.backend.entity.FeedEntry;

public interface FeedEntryService {
	public void insertFeedEntry(FeedEntry fe);
	public void removeFeedEntry();
	public FeedEntry findFeedEntry(String id);
	public List<FeedEntry> findFeedEntrybyDate(Date date);
	public List<FeedEntry> listFeedEntry();
}
