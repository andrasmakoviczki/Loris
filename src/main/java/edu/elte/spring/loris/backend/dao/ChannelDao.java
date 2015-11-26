package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Channel;

public interface ChannelDao {
	// Channel id alapján
	public Channel getChannel(String id);

	// Channel listázása
	public List<Channel> listChannel();

	// Channel listázása url alapján
	public List<Channel> findChannelbyUrl(String url);
}
