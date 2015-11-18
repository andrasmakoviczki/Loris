package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.User;

public class SubscriptionDaoImpl implements SubscriptionDao {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionDaoImpl.class);

	private GeneralEntityManagerImpl em;

	public SubscriptionDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}

	@Override
	public void insertSubscription(Subscription s) {
		em.insert(s);
		logger.info("Subscrption {} information successfully inserted.", s);
	}

	@Override
	public void removeSubscription(Subscription s) {
		em.remove(s);
		logger.info("Subscription {} information successfully removed.", s);
	}

	@Override
	public Subscription findSubscription(String id) {
		Subscription s = em.findById(Subscription.class, id);
		return s;
	}

	@Override
	public List<Subscription> listSubscription() {
		List<?> q = em.findByQuery("select s from Subscription s");

		List<Subscription> s = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Subscription) {
				s.add((Subscription) object);
			}
		}

		return s;
	}

	@Override
	public void updateSubscription(Subscription s) {
		em.merge(s);
		logger.info("Subscription {} information successfully updated.", s);
	}

	@Override
	public List<Subscription> findSubscriptionbyChannel(Channel ch) {
		String query = new String("select s from Subscription s where s.channel=:ch");
		List<?> q = em.findByQuery(query, "ch", ch);

		List<Subscription> sList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Subscription) {
				sList.add((Subscription) object);
			}
		}
		
		return sList;
	}

	@Override
	public List<Subscription> findSubscriptionbyUser(User u) {
		String query = new String("select s from Subscription s");
		List<?> q = em.findByQuery(query);

		List<Subscription> sList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Subscription) {
				Subscription s = (Subscription) object;
				if(s.getUser().getId().equals(u.getId())){
					sList.add(s);
				}
			}
		}
		
		return sList;
	}

}
