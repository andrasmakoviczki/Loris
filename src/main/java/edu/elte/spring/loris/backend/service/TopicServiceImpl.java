package edu.elte.spring.loris.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void updateTopic(Topic t) {
		tDao.merge(t);
	}

	@Override
	public Topic getTopic(String id) {
		return tDao.getTopic(id);
	}

	@Override
	public Topic getTopicbyTopicname(String tName) {
		List<Topic> tList = tDao.listTopicbyTopicName(tName);
		if (tList.isEmpty()) {
			return null;
		}

		return tList.get(0);
	}

	@Override
	public List<Topic> listTopic() {
		return tDao.listTopic();
	}

	@Override
	public List<Topic> listTopicbyTopicName(String tName) {
		return tDao.listTopicbyTopicName(tName);
	}

	@Override
	public List<Topic> listTopicbyFeedEntry(String id) {
		return tDao.listTopicbyFeedEntry(id);
	}
}
