package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManager;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;

public interface ChannelDao{
	public Channel findChannel(String id);
	public List<Channel> findChannelbyUrl(String url);
	
	public List<Channel> listChannel();
}
