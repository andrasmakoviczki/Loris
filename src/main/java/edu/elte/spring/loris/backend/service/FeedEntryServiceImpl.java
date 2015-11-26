package edu.elte.spring.loris.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.elte.spring.loris.backend.dao.FeedEntryDaoImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Topic;

@Service
public class FeedEntryServiceImpl implements FeedEntryService {

	@Autowired
	FeedEntryDaoImpl feDao;
	@Autowired
	TopicService tService;
	
	public FeedEntryServiceImpl(){
	}

	@Override
	public void createFeedEntry(FeedEntry fe) {
		feDao.insert(fe);
	}

	@Override
	public void removeFeedEntry(FeedEntry fe) {
		feDao.remove(fe);
	}
	
	@Override
	public void updateFeedEntry(FeedEntry fe) {
		FeedEntry feedEntry = feDao.getFeedEntry(fe.getId());
		feedEntry.setLabeled(fe.getLabeled());
		feedEntry.setTopic(fe.getTopic());
		feDao.merge(feedEntry);
	}

	@Override
	public Set<FeedEntry> listFeedEntry() {
		return feDao.listFeedEntry();
	}

	@Override
	public FeedEntry getFeedEntry(String id) {
		return feDao.getFeedEntry(id);
	}


	@Override
	public Set<FeedEntry> findFeedEntrybyDate(Date date) {
		return feDao.findFeedEntrybyDate(date);
	}

	@Override
	public FeedEntry getLastFeedEntrybyChannel(Channel ch) {
		return feDao.getLastFeedEntryByChannel(ch);
	}

	@Override
	public Set<FeedEntry> findFeedEntrybyChannel(Channel ch) {
		return feDao.findFeedEntrybyChannel(ch);
	}

	@Override
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate) {
		return feDao.listbyChannelAfterRegistration(ch,registrationDate);
	}

	@Override
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate, String term) {
		return feDao.findbyChannelAfterRegistration(ch,registrationDate,term);
	}
	
	@Override
	public Set<FeedEntry> findTopic(String feId, Integer topicNumber) {
		
		//FeedEntry lekérdezése id alapján
		FeedEntry fe = getFeedEntry(feId);

		Set<FeedEntry> recommendFeedEntry = new TreeSet<>();
		List<Topic> topics = new ArrayList<>();
		topics = tService.listTopicbyFeedEntry(feId);
		FeedEntry feed = feDao.getFeedEntry(feId);

		Integer currentTopicNumber = 0;
		
		//Topic keresése aktuális topic alapján
		for (Topic topic : topics) {
			List<Topic> listedTopics = tService.listTopicbyTopicName(topic.getTopicName());
			for (Topic t : listedTopics) {
				if (!t.getFeedEntry().equals(feId)) {
					FeedEntry f = getFeedEntry(t.getFeedEntry());
					if(!f.getTitle().equals(feed.getTitle())){
						recommendFeedEntry.add(f);
						currentTopicNumber = currentTopicNumber + 1;
					}
				}

				if (currentTopicNumber.equals(topicNumber)) {
					break;
				}
			}
		}

		return recommendFeedEntry;
	}
}
