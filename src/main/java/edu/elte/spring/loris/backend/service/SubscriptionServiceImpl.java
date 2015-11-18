package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.dao.SubscriptionDao;
import edu.elte.spring.loris.backend.dao.SubscriptionDaoImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.UserException;

public class SubscriptionServiceImpl implements SubscriptionService {
	
	SubscriptionDao sDao;
	UserService uService;
	ChannelService cService;

	public SubscriptionServiceImpl() {
		this.sDao = new SubscriptionDaoImpl();
		this.uService = new UserServiceImpl();
		this.cService = new ChannelServiceImpl();
	}

	@Override
	public void createSubscription(String channelUrl) throws UserException, IllegalArgumentException, ChannelException, FeedException, IOException, URISyntaxException {
		User u = uService.getCurrentUser();
		cService.createChannel(channelUrl);
		Channel ch = cService.findFirstChannelbyUrl(channelUrl);
		
		Subscription s = new Subscription();
		s.setChannel(ch);
		s.setUser(u);
		
		sDao.insertSubscription(s);
	}

	@Override
	public void removeSubscription(Subscription s) {
		sDao.removeSubscription(s);
	}

	@Override
	public Subscription findSubscription(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subscription> listSubscription() {
		return sDao.listSubscription();
	}

	@Override
	public List<Subscription> findSubscriptionbyChannel(Channel ch) {
		return sDao.findSubscriptionbyChannel(ch);
	}

	@Override
	public List<Subscription> findSubscriptionbyCurrentUser() throws UserException {
		User u = uService.getCurrentUser();
		
		if(u == null){
			u = new User();
		}
		
		return sDao.findSubscriptionbyUser(u);
	}

}
