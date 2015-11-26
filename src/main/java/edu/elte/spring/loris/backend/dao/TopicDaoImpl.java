package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Topic;
@Repository
public class TopicDaoImpl extends GeneralEntityManagerImpl<Topic> implements TopicDao {

	@Override
	public Topic getTopic(String id) {
		Topic t = findById(Topic.class, id);
		return t;
	}

	@Override
	public List<Topic> listTopic() {
		List<?> q = findByQuery("SELECT t FROM Topic t");

		List<Topic> tList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Topic) {
				tList.add((Topic) object);
			}
		}

		return tList;
	}

	@Override
	public List<Topic> listTopicbyTopicName(String tName) {
		String query = new String("SELECT t FROM Topic t WHERE t.topicName = :tname");
		List<?> q = findByQuery(query, "tname", tName);

		List<Topic> tList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Topic) {
				tList.add((Topic) object);
			}
		}

		return tList;
	}

	@Override
	public List<Topic> listTopicbyFeedEntry(String id) {
		String query = new String("SELECT t FROM Topic t WHERE t.feedEntryId = :id");
		List<?> q = findByQuery(query, "id", id);

		List<Topic> tList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Topic) {
				tList.add((Topic) object);
			}
		}

		return tList;
	}

}
