package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.User;

public interface SubscriptionDao {
	public void insertSubscription(Subscription s);
	public void removeSubscription(Subscription s);
	public void updateSubscription(Subscription s);
	public Subscription findSubscription(String id);
	public List<Subscription> findSubscriptionbyChannel(Channel ch);
	public List<Subscription> findSubscriptionbyUser(User u);
	public List<Subscription> listSubscription();
}
