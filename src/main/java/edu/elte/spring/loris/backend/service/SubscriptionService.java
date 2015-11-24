package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.UserException;

public interface SubscriptionService {
	public void createSubscription(String channelUrl) throws UserException, IllegalArgumentException, ChannelException, FeedException, IOException, URISyntaxException;
	public void removeSubscription(String ch) throws UserException;
	public Subscription findSubscription(String id);
	public List<Subscription> findSubscriptionbyChannel(Channel ch);
	public List<Subscription> findSubscriptionbyCurrentUser() throws UserException;
	public List<Subscription> listSubscription();
	public List<Channel> listChannelbyCurrentUser() throws UserException;
	public Map<Channel,Set<FeedEntry>> listFeedEntrybyChannel() throws UserException;
	public Map<Channel,Set<FeedEntry>> findFeedEntrybyChannel(String term) throws UserException;
	public void Download();
}
