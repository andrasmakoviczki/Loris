package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Topic;

public interface TopicService {
	public void createTopic(Topic t);
	public void removeTopic(Topic t);
	public Topic findTopic(String id);
	public Topic findTopicbyTopicname(String tName);
	public List<Topic> listTopic();
	public List<Topic> listTopicbyTopicName(String tName);
}
