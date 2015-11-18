package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

public class FeedEntryDaoImpl implements FeedEntryDao {

	private static final Logger logger = LoggerFactory.getLogger(FeedEntryDaoImpl.class);

	private GeneralEntityManagerImpl em;

	public FeedEntryDaoImpl() {
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
		logger.info("FeedEntry {} information successfully inserted.", fe);
	}

	@Override
	public void removeFeedEntry(FeedEntry fe) {
		em.remove(fe);
		logger.info("FeedEntry {} information successfully removed.", fe);
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
		List<?> q = em.findByQuery(query, "d", date);

		List<FeedEntry> fe = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				fe.add((FeedEntry) object);
			}
		}

		return fe;
	}

	@Override
	public FeedEntry selectLastFeedEntry() {

		String query = "select fe from FeedEntry fe";

		List<FeedEntry> results = em.findByTypedQuery(query, FeedEntry.class).getResultList();

		if (results.isEmpty()) {
			return null;
		}

		FeedEntry last = Collections.max(results, new Comparator<FeedEntry>() {
			@Override
			public int compare(FeedEntry o1, FeedEntry o2) {
				return o1.getPublishDate().compareTo(o2.getPublishDate());
			}
		});

		return last;
	}

	@Override
	public void updateFeedEntry(FeedEntry fe) {
		em.merge(fe);
		logger.info("FeedEntry {} information successfully updated.", fe);
	}

	@Override
	public List<FeedEntry> findFeedEntrybyChannel(Channel ch) {
		String query = new String("select fe from FeedEntry fe where fe.channelId=:ch");
		List<?> q = em.findByQuery(query,"ch",ch.getId());
		
		//List<?> q = em.findByQuery("select fe from FeedEntry fe");

		List<FeedEntry> feList = new ArrayList<>();
		String chId = ch.getId();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				FeedEntry fe = (FeedEntry) object;
				//String feCh = fe.getChannel().getId();
				//if(chId.equals(feCh)){
					feList.add(fe);
				//}				
			}
		}

		return feList;
	}

	@Override
	public List<FeedEntry> listFeedEntry2(String a) {
		List<?> q = em.findByQuery("select fe from FeedEntry fe");

		List<FeedEntry> fe = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				fe.add((FeedEntry) object);
			}
		}

		return fe;
	}

}
