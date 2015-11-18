package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Topic;

public class TopicDaoImpl implements TopicDao {

	private static final Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);

	private GeneralEntityManagerImpl em;

	public TopicDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}

	@Override
	public void insertTopic(Topic t) {
		em.insert(t);
		logger.info("Topic {} information successfully inserted.", t);
	}

	@Override
	public void removeTopic(Topic t) {
		em.remove(t);
		logger.info("Topic {} information successfully removed.", t);
	}

	@Override
	public Topic findTopic(String id) {
		Topic t = em.findById(Topic.class, id);
		return t;
	}

	@Override
	public List<Topic> listTopic() {
		List<?> q = em.findByQuery("select t from Topic t");

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
		List<?> q = em.findByQuery(query, "tname", tname);

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
	public void updateTopic(Topic t) {
		em.merge(t);
		logger.info("Topic {} information successfully updated.", t);
	}

	@Override
	public List<Topic> listTopicbyTopicName(String tName) {
		String query = new String("select t from " + Topic.class.getSimpleName() + " t where t.topicName = :tname");
		List<?> q = em.findByQuery(query, "tname", tName);

		List<Topic> tList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Topic) {
				tList.add((Topic) object);
			}
		}

		return tList;
	}

}
