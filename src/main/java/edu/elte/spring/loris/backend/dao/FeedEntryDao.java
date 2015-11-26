package edu.elte.spring.loris.backend.dao;

import java.util.Date;
import java.util.Set;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public interface FeedEntryDao {
	// Category id alapján
	public FeedEntry getFeedEntry(String id);

	// Legutolsó FeedEntry bejegyzés visszaadása channel alapján
	public FeedEntry getLastFeedEntryByChannel(Channel ch);

	// FeedEntry listázása
	public Set<FeedEntry> listFeedEntry();

	// FeedEntry listázása dátum alapján
	public Set<FeedEntry> findFeedEntrybyDate(Date date);

	// FeedEntry listázása channel alapján
	public Set<FeedEntry> findFeedEntrybyChannel(Channel ch);

	// FeedEntry listázása chanel és regisztráció időpontja
	public Set<FeedEntry> listbyChannelAfterRegistration(Channel ch, Date registrationDate);

	// FeedEntry keresése chanel és regisztráció időpontja alapján
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate, String term);
}