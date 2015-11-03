package edu.elte.spring.loris.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public class FeedEntryServiceImpl implements FeedEntryService {

	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);

	private GeneralEntityManagerImpl em;

	public FeedEntryServiceImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}

	@Override
	public void insertFeedEntry(FeedEntry fe) {
		em.insert(fe);
		logger.info("Channel {} information successfully inserted.", fe);
	}
	
	@Override
	public void removeFeedEntry() {
		// TODO Auto-generated method stub

	}

	@Override
	public FeedEntry findFeedEntry(String id) {
		FeedEntry fe = em.findById(FeedEntry.class, id);
		return fe;
	}

	@Override
	public List<FeedEntry> listFeedEntry() {
		List<?> q = em.findByQuery("select fe from FeedEntry fe");
		
		List<FeedEntry> fe = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				fe.add((FeedEntry) object);
			}
		}
		
		return fe;
	}

	@Override
	public List<FeedEntry> findFeedEntrybyDate(Date date) {
		String query = new String("select fe from " + FeedEntry.class.getSimpleName() + " fe where fe.link = :d");
		List<?> q = em.findByQuery(query,"d",date);
		
		List<FeedEntry> fe = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				fe.add((FeedEntry) object);
			}
		}
		
		return fe;
	}

}
