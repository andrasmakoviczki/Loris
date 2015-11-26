package edu.elte.spring.loris.backend.service;

import java.util.Date;
import java.util.Set;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public interface FeedEntryService {
	// FeedEntry létrehozása
	public void createFeedEntry(FeedEntry fe);

	// FeedEntry törlése
	public void removeFeedEntry(FeedEntry fe);

	// FeedEntry frissítése
	public void updateFeedEntry(FeedEntry fe);

	// FeedEntry listázása
	public Set<FeedEntry> listFeedEntry();

	// Utolsó FeedEntry bejegyzése
	public FeedEntry getLastFeedEntrybyChannel(Channel ch);

	// FeedEntry id alapján
	public FeedEntry getFeedEntry(String id);

	// FeedEntry listázása dátum alapján
	public Set<FeedEntry> findFeedEntrybyDate(Date date);

	// FeedEntry listázása csatorna alapján
	public Set<FeedEntry> findFeedEntrybyChannel(Channel ch);

	// FeedEntry listázása csatorna és regisztráció dátuma alapján
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate);

	// FeedEntry keresése csatorna és regisztráció dátuma alapján
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate, String term);

	// FeedEntry keresése topik alapján
	public Set<FeedEntry> findTopic(String feId, Integer topicNumber);
}
