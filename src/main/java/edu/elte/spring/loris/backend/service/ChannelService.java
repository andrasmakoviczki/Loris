package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.util.List;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.util.ChannelException;

public interface ChannelService {
	public void insertChannel(String channelUrl) throws ChannelException,IllegalArgumentException, FeedException, IOException;
	public void updateChannel(Channel ch);
	public void removeChannel();
	public Channel findChannel(String id);
	public List<Channel> listChannel();
}
