package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManager;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.User;

public interface SubscriptionDao {
	public Subscription findSubscription(String id);
	public List<Subscription> listSubscriptionbyChannel(Channel ch);
	public Subscription getSubscriptionbyChannelandUser(Channel ch,User u);
	public List<Subscription> listSubscriptionbyUser(User u);
	public List<Subscription> listSubscription();
}
