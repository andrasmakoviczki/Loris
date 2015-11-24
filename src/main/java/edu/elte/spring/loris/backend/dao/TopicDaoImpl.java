package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Topic;
import org.springframework.stereotype.Repository;
@Repository
public class TopicDaoImpl extends GeneralEntityManagerImpl<Topic> implements TopicDao {

	private static final Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);


	/*public TopicDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}*/

	@Override
	public Topic findTopic(String id) {
		Topic t = findById(Topic.class, id);
		return t;
	}

	@Override
	public List<Topic> listTopic() {
		List<?> q = findByQuery("select t from Topic t");

		List<Topic> tList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Topic) {
				tList.add((Topic) object);
			}
		}

		return tList;
	}

	@Override
	public Topic findbyTopicName(String tname) {
		String query = new String("select t from " + Topic.class.getSimpleName() + " t where t.topicname=:tname");
		List<?> q = findByQuery(query, "tname", tname);

		List<Topic> tList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Topic) {
				tList.add((Topic) object);
			}
		}

		if (tList.isEmpty()) {
			return null;
		}

		return tList.get(0);
	}

	@Override
	public List<Topic> listTopicbyTopicName(String tName) {
		String query = new String("select t from " + Topic.class.getSimpleName() + " t where t.topicName = :tname");
		List<?> q = findByQuery(query, "tname", tName);

		List<Topic> tList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Topic) {
				tList.add((Topic) object);
			}
		}

		return tList;
	}

}
