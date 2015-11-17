package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.dao.ChannelDao;
import edu.elte.spring.loris.backend.dao.ChannelDaoImpl;
import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.Topic;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.URLNormailze;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.backend.util.rssFeedDownload;

public class ChannelServiceImpl implements ChannelService {

	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);
	ChannelDao chDao;
	UserService uService;
	SubscriptionService sService;
	FeedEntryService feService;

	public ChannelServiceImpl() {
		this.chDao = new ChannelDaoImpl();
		this.uService = new UserServiceImpl();
	}

	public void createChannel(String channelUrl)
			throws IllegalArgumentException, FeedException, IOException, ChannelException, UserException, URISyntaxException {
		
		if (!findChannelbyUrl(channelUrl).isEmpty()) {
			throw new ChannelException(String.format("Channel already exists: %s", channelUrl));
		}

		Channel ch = new Channel();
		URLNormailze u = new URLNormailze();
		
		URL ul = u.normailze(channelUrl);
		rssFeedDownload r = new rssFeedDownload(ul);
		r.ChannelBuild(ch);

		Date createDate = new Date();
		ch.setCreateDate(createDate);
		ch.setLink(ul.toString());

		chDao.insertChannel(ch);

	}

	public void removeChannel(String chId) {
		Channel ch = chDao.findChannel(chId);
		chDao.removeChannel(ch);
		/*List<Subscription> sList = sService.findSubscriptionbyChannel(ch);

		if (sList.size() > 1){
			List<FeedEntry> feList = feService.findFeedEntrybyChannel(ch);
			for (FeedEntry feedEntry : feList) {
				//List<Topic> tList =  
			}
		}*/
	}

	public Channel findChannel(String id) {
		return null;
	}

	@Override
	public List<Channel> listChannel() {
		List<Channel> cList = chDao.listChannel();
		if(cList == null){
			cList = new ArrayList<>();
		}
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
}
