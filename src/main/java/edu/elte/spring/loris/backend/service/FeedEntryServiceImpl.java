package edu.elte.spring.loris.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.FeedEntryDao;
import edu.elte.spring.loris.backend.dao.FeedEntryDaoImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.Topic;
import edu.elte.spring.loris.backend.util.UserException;

public class FeedEntryServiceImpl implements FeedEntryService {

	private static final Logger logger = LoggerFactory.getLogger(FeedEntryServiceImpl.class);

	FeedEntryDao feDao;
	TopicService tService;
	SubscriptionService sService;
	ChannelService cService;

	public FeedEntryServiceImpl() {
		this.feDao = new FeedEntryDaoImpl();
		this.tService = new TopicServiceImpl();
		this.sService = new SubscriptionServiceImpl();
		this.cService = new ChannelServiceImpl();
	}

	@Override
	public void createFeedEntry(FeedEntry fe) {
		feDao.insertFeedEntry(fe);
	}
	
	@Override
	public void removeFeedEntry(FeedEntry fe) {
		feDao.removeFeedEntry(fe);
	}

	@Override
	public FeedEntry findFeedEntry(String id) {
		return feDao.findFeedEntry(id);
	}

	@Override
	public List<FeedEntry> listFeedEntry() {
		return feDao.listFeedEntry();
	}

	@Override
	public List<FeedEntry> findFeedEntrybyDate(Date date) {
		return null;
	}

	@Override
	public FeedEntry getLastFeedEntry() {
		return feDao.selectLastFeedEntry();
	}

	@Override
	public void updateFeedEntry(FeedEntry fe) {
		FeedEntry feedEntry = feDao.findFeedEntry(fe.getId());
		feedEntry.setLabeled(fe.getLabeled());
		feedEntry.setTopic(fe.getTopic());
		feDao.updateFeedEntry(feedEntry);
	}

	@Override
	public List<FeedEntry> findTopic(String feId, Integer topicNumber) {
		FeedEntry fe = findFeedEntry(feId);
		
		List<FeedEntry> recommendFeedEntry = new ArrayList<>();
		List<Topic> topics = fe.getTopic();
		
		
		Integer currentTopicNumber = 0;
		for (Topic topic : topics) {
			List<Topic> listedTopics = tService.listTopicbyTopicName(topic.getTopicName());
			for (Topic t : listedTopics) {
				if(t.getFeedEntry() != feId){
					FeedEntry f = findFeedEntry(t.getFeedEntry()); 
					recommendFeedEntry.add(f);
					currentTopicNumber = currentTopicNumber + 1;
				}
				
				if(currentTopicNumber == topicNumber){
					break;
				}
			}
		}

		return recommendFeedEntry;
	}

	@Override
	public List<FeedEntry> findFeedEntrybyChannel(Channel ch) {
		return feDao.findFeedEntrybyChannel(ch);
	}

	@Override
	public List<List<FeedEntry>> findFeedEntrybyUser() throws UserException {
		
		List<Subscription> sList = sService.findSubscriptionbyCurrentUser();
		List<Channel> cList = new ArrayList<>();
		for (Subscription s : sList) {
			cList.add(s.getChannel());
		}
			
		List<List<FeedEntry>> feList = new ArrayList<>();
				
		for (Channel ch : cList) {
			feList.add(findFeedEntrybyChannel(ch));
		}
			
		return feList;
	}
}
