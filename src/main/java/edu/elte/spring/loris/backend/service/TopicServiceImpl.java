package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.dao.TopicDao;
import edu.elte.spring.loris.backend.dao.TopicDaoImpl;
import edu.elte.spring.loris.backend.entity.Topic;

public class TopicServiceImpl implements TopicService {

	TopicDao tDao;

	public TopicServiceImpl() {
		this.tDao = new TopicDaoImpl();
	}

	@Override
	public void createTopic(Topic t) {
		tDao.insertTopic(t);
	}

	@Override
	public void removeTopic(Topic t) {
		tDao.removeTopic(t);
	}

	@Override
	public Topic findTopic(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic findTopicbyTopicname(String tName) {
		return tDao.findbyTopicName(tName);
	}

	@Override
	public List<Topic> listTopic() {
		return tDao.listTopic();
	}

	@Override
	public List<Topic> listTopicbyTopicName(String tName) {
		return tDao.listTopicbyTopicName(tName);
	}

}
