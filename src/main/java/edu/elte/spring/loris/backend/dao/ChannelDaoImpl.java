package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import org.springframework.stereotype.Repository;
@Repository("ChannelDao")
public class ChannelDaoImpl extends GeneralEntityManagerImpl<Channel> implements ChannelDao {

	private static final Logger logger = LoggerFactory.getLogger(ChannelDaoImpl.class);

	/*public ChannelDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}*/



	public Channel findChannel(String id) {
		Channel ch = findById(Channel.class, id);
		return ch;
	}

	@Override
	public List<Channel> listChannel() {
		List<?> q = findByQuery("select ch from Channel ch");

		List<Channel> ch = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Channel) {
				ch.add((Channel) object);
			}
		}

		return ch;
	}

	@Override
	public List<Channel> findChannelbyUrl(String url) {
		String query = new String("select ch from " + Channel.class.getSimpleName() + " ch where ch.link = :l");
		List<?> q = findByQuery(query, "l", url);

		List<Channel> ch = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Channel) {
				ch.add((Channel) object);
			}
		}

		return ch;
	}
}
