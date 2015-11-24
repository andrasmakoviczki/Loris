package edu.elte.spring.loris.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.elte.spring.loris.backend.dao.TopicDao;
import edu.elte.spring.loris.backend.dao.TopicDaoImpl;
import edu.elte.spring.loris.backend.entity.Topic;

@Service
public class TopicServiceImpl implements TopicService {


	@Autowired
	TopicDaoImpl tDao;

	public TopicServiceImpl() {
	}

	@Override
	public void createTopic(Topic t) {
		tDao.insert(t);
	}

	@Override
	public void removeTopic(Topic t) {
		tDao.remove(t);
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
