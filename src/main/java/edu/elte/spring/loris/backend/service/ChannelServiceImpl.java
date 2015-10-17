package edu.elte.spring.loris.backend.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.HomeController;
import edu.elte.spring.loris.backend.dao.GeneralEntityManager;
import edu.elte.spring.loris.backend.dao.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;

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
	public void insertChannel(String channelUrl) {
		//em.insert(new Channel(null, channelUrl, null, null));
		Date d = new Date();
		em.insert(new Channel("szolo", channelUrl, "barack", d));
		logger.info("Channel {} information successfully inserted.", channelUrl);
	}

	public void removeChannel() {
	}

	public Channel findChannel(String id) {
		Channel ch = em.findById(Channel.class, id);
		return ch;
	}
}
