package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.util.exception.ChannelException;

public interface ChannelService {
	// Channel létrehozása
	public void createChannel(Channel ch);

	// Channel törlése
	public void removeChannel(Channel ch);

	// Channel frissítése
	public void updateChannel(Channel ch);

	// Channel listázása
	public List<Channel> listChannel();

	// Channel listázása url alapján
	public List<Channel> findChannelbyUrl(String url);

	// Channel id alapján
	public Channel getChannel(String id);

	// Channel url alapján
	public Channel getChannelbyUrl(String url) throws ChannelException;
}
