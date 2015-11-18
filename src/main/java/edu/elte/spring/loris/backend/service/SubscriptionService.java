package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.UserException;

public interface SubscriptionService {
	public void createSubscription(String channelUrl) throws UserException, IllegalArgumentException, ChannelException, FeedException, IOException, URISyntaxException;
	public void removeSubscription(Subscription s);
	public Subscription findSubscription(String id);
	public List<Subscription> findSubscriptionbyChannel(Channel ch);
	public List<Subscription> findSubscriptionbyCurrentUser() throws UserException;
	public List<Subscription> listSubscription();
}
