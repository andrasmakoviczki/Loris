package edu.elte.spring.loris.backend.service;

import edu.elte.spring.loris.backend.entity.Channel;

public interface ChannelService {
	public void insertChannel(String channelUrl);
	public void removeChannel();
	public Channel findChannel(String id);
}
