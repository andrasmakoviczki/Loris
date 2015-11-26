package edu.elte.spring.loris.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.elte.spring.loris.backend.dao.ChannelDaoImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.util.exception.ChannelException;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	ChannelDaoImpl chDao;

	public ChannelServiceImpl() {
	}
	
	@Override
	public void createChannel(Channel ch) {
		chDao.insert(ch);		
	}

	@Override
	public void removeChannel(Channel ch) {
		chDao.remove(ch);		
	}

	@Override
	public void updateChannel(Channel ch) {
		chDao.merge(ch);
	}

	public Channel getChannel(String id) {
		return chDao.getChannel(id);
	}

	@Override
	public List<Channel> listChannel() {
		return chDao.listChannel();
	}

	@Override
	public List<Channel> findChannelbyUrl(String url) {
		return chDao.findChannelbyUrl(url);
	}

	@Override
	public Channel getChannelbyUrl(String url) throws ChannelException {
		List<Channel> ch = chDao.findChannelbyUrl(url);
		if (ch.isEmpty()){
			throw new ChannelException("Channel not exists: " + url);
		}
		return ch.get(0);
	}

}
