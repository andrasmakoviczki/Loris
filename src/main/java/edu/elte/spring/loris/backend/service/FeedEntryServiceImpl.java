package edu.elte.spring.loris.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.elte.spring.loris.backend.dao.FeedEntryDao;
import edu.elte.spring.loris.backend.dao.FeedEntryDaoImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.Topic;
import edu.elte.spring.loris.backend.util.UserException;

@Service
public class FeedEntryServiceImpl implements FeedEntryService {

	private static final Logger logger = LoggerFactory.getLogger(FeedEntryServiceImpl.class);

	@Autowired
	FeedEntryDaoImpl feDao;
	@Autowired
	TopicService tService;

	@Override
	public void createFeedEntry(FeedEntry fe) {
		feDao.insert(fe);
	}

	@Override
	public void removeFeedEntry(FeedEntry fe) {
		feDao.remove(fe);
	}

	@Override
	public FeedEntry findFeedEntry(String id) {
		return feDao.findFeedEntry(id);
	}

	@Override
	public Set<FeedEntry> listFeedEntry() {
		return feDao.listFeedEntry();
	}

	@Override
	public Set<FeedEntry> findFeedEntrybyDate(Date date) {
		return null;
	}

	@Override
	public FeedEntry getLastFeedEntrybyChannel(Channel ch) {
		return feDao.selectLastFeedEntryByChannel(ch);
	}

	@Override
	public void updateFeedEntry(FeedEntry fe) {
		FeedEntry feedEntry = feDao.findFeedEntry(fe.getId());
		feedEntry.setLabeled(fe.getLabeled());
		feedEntry.setTopic(fe.getTopic());
		feDao.merge(feedEntry);
	}

	@Override
	public Set<FeedEntry> findTopic(String feId, Integer topicNumber) {
		FeedEntry fe = findFeedEntry(feId);

		Set<FeedEntry> recommendFeedEntry = new TreeSet<>();
		List<Topic> topics = fe.getTopic();

		Integer currentTopicNumber = 0;
		for (Topic topic : topics) {
			List<Topic> listedTopics = tService.listTopicbyTopicName(topic.getTopicName());
			for (Topic t : listedTopics) {
				// t.getFeedEntry()
				if (t.getFeedEntry() != feId) {
					FeedEntry f = findFeedEntry(t.getFeedEntry());
					recommendFeedEntry.add(f);
					currentTopicNumber = currentTopicNumber + 1;
				}

				if (currentTopicNumber == topicNumber) {
					break;
				}
			}
		}

		return recommendFeedEntry;
	}

	@Override
	public Set<FeedEntry> findFeedEntrybyChannel(Channel ch) {
		return feDao.findFeedEntrybyChannel(ch);
	}

	@Override
	public List<Set<FeedEntry>> findFeedEntrybyUser() throws UserException {

		/*
		 * List<Subscription> sList = sService.findSubscriptionbyCurrentUser();
		 * List<Channel> cList = new ArrayList<>(); for (Subscription s : sList)
		 * { cList.add(s.getChannel()); }
		 * 
		 * List<Set<FeedEntry>> feList = new ArrayList<>();
		 * 
		 * for (Channel ch : cList) { feList.add(findFeedEntrybyChannel(ch)); }
		 */

		return null;
	}

	@Override
	public Set<FeedEntry> listbyChannelAfterRegistration(Channel ch, Date registrationDate) {
		return feDao.listbyChannelAfterRegistration(ch,registrationDate);
	}

	@Override
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate, String term) {
		return feDao.findbyChannelAfterRegistration(ch,registrationDate,term);
	}
}
