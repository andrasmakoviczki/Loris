package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Topic;

public interface TopicService {
	// Topic létrehozása
	public void createTopic(Topic t);

	// Topic törlése
	public void removeTopic(Topic t);

	// Topic frissítése
	public void updateTopic(Topic t);

	// Topic id alapján
	public Topic getTopic(String id);

	// Topic topiknév alapján
	public Topic getTopicbyTopicname(String tName);

	// Topic listázása
	public List<Topic> listTopic();

	// Topic listázása topiknév alapján
	public List<Topic> listTopicbyTopicName(String tName);
	
	// Topic listázása feedEntryId alapján
	public List<Topic> listTopicbyFeedEntry(String id);
}
