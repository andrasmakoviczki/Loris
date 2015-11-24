package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.dao.ChannelDao;
import edu.elte.spring.loris.backend.dao.ChannelDaoImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.URLNormailze;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.backend.util.rssFeedDownload;

@Service
public class ChannelServiceImpl implements ChannelService {


	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);
	
	@Autowired
	ChannelDaoImpl chDao;

	public ChannelServiceImpl() {
	}

	public void createChannel(String channelUrl)
			throws IllegalArgumentException, FeedException, IOException, ChannelException, UserException, URISyntaxException {
		Channel ch = new Channel();
		chDao.insert(ch);
	}

	public void removeChannel(String chId) {
		Channel ch = chDao.findChannel(chId);
		chDao.remove(ch);
		
		/*List<Subscription> sList = sService.findSubscriptionbyCurrentUser();
		
		if(sList.size() > 1){
			for (Subscription subscription : sList) {
				if(subscription.getChannel().equals(ch)){
					sService.removeSubscription(subscription);
				}
			}
		} else if (sList.size() == 1){
			List<FeedEntry> feList = feService.findFeedEntrybyChannel(ch);
			for (FeedEntry fe : feList) {
				for (Category ca : fe.getCategory()) {
					caService.removeCategory(ca);
				}
				for (Topic t : fe.getTopic()) {
					tService.removeTopic(t);
				}
				feService.removeFeedEntry(fe);
			}
			sService.removeSubscription(sList.get(0));
			chDao.removeChannel(ch);
		}*/
		
	}

	public Channel findChannel(String id) {
		return chDao.findChannel(id);
	}

	@Override
	public List<Channel> listChannel() {
		List<Channel> cList = chDao.listChannel();
		
		
		return cList;
	}

	@Override
	public void updateChannel(Channel ch) {
	}

	@Override
	public List<Channel> findChannelbyUrl(String url) {
		return chDao.findChannelbyUrl(url);
	}

	@Override
	public Channel findFirstChannelbyUrl(String url) throws ChannelException {
		List<Channel> ch = chDao.findChannelbyUrl(url);
		if (ch.isEmpty()){
			throw new ChannelException("Channel not exists: " + url);
		}
		return ch.get(0);
	}



	@Override
	public void createChannel(Channel ch) {
		chDao.insert(ch);		
	}

	@Override
	public void removeChannel(Channel ch) {
		chDao.remove(ch);		
	}


}
