package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Topic;

public interface TopicDao {
	public void insertTopic(Topic t);
	public void updateTopic(Topic t);
	public Topic findTopic(String id);
	public Topic findbyTopicName(String tName);
	public List<Topic> listTopic();
	public List<Topic> listTopicbyTopicName(String tName);
	void removeTopic(Topic t);
}
