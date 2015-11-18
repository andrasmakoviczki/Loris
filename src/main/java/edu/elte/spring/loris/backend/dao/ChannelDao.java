package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Channel;

public interface ChannelDao {
	public void insertChannel(Channel ch);
	public void updateChannel(Channel ch);
	public void removeChannel(Channel ch);
	public Channel findChannel(String id);
	public List<Channel> findChannelbyUrl(String url);
	public List<Channel> listChannel();
}
