package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.User;

public interface SubscriptionDao {
	// Subscription id alapján
	public Subscription getSubscription(String id);

	// Subscription listázása
	public List<Subscription> listSubscription();

	// Subscription listázása csatorna alapján
	public List<Subscription> findSubscriptionbyChannel(Channel ch);

	// Subscription listázása user alapján
	public List<Subscription> findSubscriptionbyUser(User u);

	// Subscription listázása csatorna és user alapján
	public Subscription getSubscriptionbyChannelandUser(Channel ch, User u);
}
