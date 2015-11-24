package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionDaoImpl extends GeneralEntityManagerImpl<Subscription> implements SubscriptionDao {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionDaoImpl.class);

	@Override
	public Subscription findSubscription(String id) {
		Subscription s = findById(Subscription.class, id);
		return s;
	}

	@Override
	public List<Subscription> listSubscription() {
		List<?> q = findByQuery("select s from Subscription s");

		List<Subscription> s = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Subscription) {
				s.add((Subscription) object);
			}
		}

		return s;
	}

	@Override
	public List<Subscription> listSubscriptionbyChannel(Channel ch) {
		String query = new String("select s from Subscription s");
		List<?> q = findByQuery(query);

		List<Subscription> sList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Subscription) {
				Subscription s = (Subscription) object;
				if (s.getChannel().getId().equals(ch.getId())) {
					sList.add(s);
				}
			}
		}

		return sList;
	}

	@Override
	public List<Subscription> listSubscriptionbyUser(User u) {
		String query = new String("select s from Subscription s");
		List<?> q = findByQuery(query);

		List<Subscription> sList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Subscription) {
				Subscription s = (Subscription) object;
				if (s.getUser().getId().equals(u.getId())) {
					sList.add(s);
				}
			}
		}

		return sList;
	}
	
		@Override
		public Subscription getSubscriptionbyChannelandUser(Channel ch, User u) {
		String query = new String("select s from Subscription s");
		List<?> q = findByQuery(query);

		List<Subscription> sList = new ArrayList<>();	
		
		for (Object object : q) {
			if (object instanceof Subscription) {
				Subscription s = (Subscription) object;
				if (s.getUser().getId().equals(u.getId()) && s.getChannel().getId().equals(ch.getId())) {
					sList.add(s);
				}
			}
		}

		if(sList.size() != 1){
			return null;
		}
		
		return sList.get(0);
	}


}
