package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;

public class ChannelDaoImpl implements ChannelDao {

	private static final Logger logger = LoggerFactory.getLogger(ChannelDaoImpl.class);

	private GeneralEntityManagerImpl em;

	public ChannelDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}

	public void insertChannel(Channel ch) {
		em.insert(ch);
		logger.info("Channel {} information successfully inserted.", ch);
	}

	public void removeChannel(Channel ch) {
		em.remove(ch);
		logger.info("Channel {} information successfully removed.", ch);
	}

	public Channel findChannel(String id) {
		Channel ch = em.findById(Channel.class, id);
		return ch;
	}

	@Override
	public List<Channel> listChannel() {
		List<?> q = em.findByQuery("select ch from Channel ch");

		List<Channel> ch = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Channel) {
				ch.add((Channel) object);
			}
		}

		return ch;
	}

	@Override
	public void updateChannel(Channel ch) {
		em.merge(ch);
		logger.info("Channel {} information successfully updated.", ch);
	}

	@Override
	public List<Channel> findChannelbyUrl(String url) {
		String query = new String("select ch from " + Channel.class.getSimpleName() + " ch where ch.link = :l");
		List<?> q = em.findByQuery(query, "l", url);

		List<Channel> ch = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Channel) {
				ch.add((Channel) object);
			}
		}

		return ch;
	}
}
