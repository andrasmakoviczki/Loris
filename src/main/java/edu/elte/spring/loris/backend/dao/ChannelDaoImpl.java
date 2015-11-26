package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
@Repository("ChannelDao")
public class ChannelDaoImpl extends GeneralEntityManagerImpl<Channel> implements ChannelDao {

	public Channel getChannel(String id) {
		Channel ch = findById(Channel.class, id);
		return ch;
	}

	@Override
	public List<Channel> listChannel() {
		List<?> q = findByQuery("SELECT ch FROM Channel ch");

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
		String query = new String("SELECT ch FROM " + Channel.class.getSimpleName() + " ch WHERE ch.link = :url");
		List<?> q = findByQuery(query, "url", url);

		List<Channel> ch = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Channel) {
				ch.add((Channel) object);
			}
		}

		return ch;
	}
}
