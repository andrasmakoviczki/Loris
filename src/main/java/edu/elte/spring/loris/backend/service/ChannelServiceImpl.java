package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.dao.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.rssHandle.rssFeedDownload;
import edu.elte.spring.loris.backend.util.ChannelException;

public class ChannelServiceImpl implements ChannelService {

	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);
	
	private GeneralEntityManagerImpl em;

	public ChannelServiceImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}

	//TODO: RSS csatornáról kipótolni a hiányzó adatokat
	public void insertChannel(String channelUrl) throws IllegalArgumentException, FeedException, IOException, ChannelException{
		//TODO: rendelje felhasználóhoz a csatornát
		if (!findChannelByLink(channelUrl).isEmpty()){
			throw new ChannelException(String.format("Channel already exists: %s", channelUrl));
		}
		rssFeedDownload r = new rssFeedDownload(channelUrl);
		Channel ch  = new Channel();
		ch.setLink(channelUrl);
		r.ChannelBuild(ch);
		em.insert(ch);
		logger.info("Channel {} information successfully inserted.", ch);
	}

	public void removeChannel() {
	}

	public Channel findChannel(String id) {
		Channel ch = em.findById(Channel.class, id);
		return ch;
	}
	
	public List<Channel> findChannelByLink(String link) {
		String query = new String("select ch from " + Channel.class.getSimpleName() + " ch where ch.link = :l");
		List<?> ch = em.findByQuery(query,"l",link);
		return (List<Channel>) ch;
	}

	@Override
	public List<Channel> listChannel() {
		List<?> ch = em.findByQuery("select ch from Channel ch");
		return (List<Channel>) ch;
	}

	@Override
	public void updateChannel(Channel ch) {
		em.merge(ch);	
		logger.info("Channel {} information successfully updated.", ch);
	}
}
