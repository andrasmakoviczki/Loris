package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Topic;

public interface TopicDao {
	// Topic id alapján
	public Topic getTopic(String id);

	// Topic listázása
	public List<Topic> listTopic();

	// Topic listázása topiknév alapján
	public List<Topic> listTopicbyTopicName(String tName);

	// Topic listázása feedEntryId alapján
	public List<Topic> listTopicbyFeedEntry(String id);
}
