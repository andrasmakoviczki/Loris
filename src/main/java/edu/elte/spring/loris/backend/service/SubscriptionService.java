package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.util.exception.ChannelException;
import edu.elte.spring.loris.backend.util.exception.UserException;

public interface SubscriptionService {
	// Subscription létrehozása
	public void createSubscription(Subscription s);

	// Subscription létrehozása url alapján
	public void createSubscription(String channelUrl) throws UserException, IllegalArgumentException, ChannelException,
			FeedException, IOException, URISyntaxException;

	// Subscription törlése
	public void removeSubscription(Subscription s);

	// Subscription törlése channel alapján
	public void removeSubscription(String ch) throws UserException;

	// Subscription frissítése
	public void updateSubscription(Subscription s);

	// Subscription id alapján
	public Subscription getSubscription(String id);

	// Subscription listázása
	public List<Subscription> listSubscription();

	// Subscription listázása channel alapján
	public List<Subscription> findSubscriptionbyChannel(Channel ch);

	// Subscription listázása aktuális user alapján
	public List<Subscription> findSubscriptionbyCurrentUser() throws UserException;

	// Csatorna listázása aktuális user alapján
	public List<Channel> findChannelbyCurrentUser() throws UserException;

	// FeedEntry listázása channel szerint rendezve
	public Map<Channel, Set<FeedEntry>> findFeedEntrybyChannel() throws UserException;

	// FeedEntry keresése channel szerint rendezve
	public Map<Channel, Set<FeedEntry>> searchFeedEntrybyChannel(String term) throws UserException;

	// Új FeedEntry gyűjtése
	public void getNewFeeds() throws IllegalArgumentException, MalformedURLException, FeedException, IOException;
}
