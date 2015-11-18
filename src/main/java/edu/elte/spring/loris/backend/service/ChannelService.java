package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.UserException;

public interface ChannelService {
	public void createChannel(String channelUrl) throws ChannelException,IllegalArgumentException, FeedException, IOException, UserException, URISyntaxException;
	public void updateChannel(Channel ch);
	public void removeChannel(String chId) throws UserException;
	public Channel findChannel(String id);
	public Channel findFirstChannelbyUrl(String url) throws ChannelException;
	public List<Channel> findChannelbyUrl(String url);
	public List<Channel> findChannelbyCurrentUser() throws UserException;
	public List<Channel> listChannel();
}
