package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;

@Repository
public class FeedEntryDaoImpl extends GeneralEntityManagerImpl<FeedEntry> implements FeedEntryDao {

	private static final Logger logger = LoggerFactory.getLogger(FeedEntryDaoImpl.class);

	@Override
	public FeedEntry findFeedEntry(String id) {
		FeedEntry fe = findById(FeedEntry.class, id);
		return fe;
	}

	@Override
	public Set<FeedEntry> listFeedEntry() {
		List<?> q = findByQuery("select fe from FeedEntry fe");

		Set<FeedEntry> fe = new TreeSet<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				fe.add((FeedEntry) object);
			}
		}

		return fe;
	}

	@Override
	public Set<FeedEntry> findFeedEntrybyDate(Date date) {
		String query = new String("select fe from " + FeedEntry.class.getSimpleName() + " fe where fe.link = :d");
		List<?> q = findByQuery(query, "d", date);

		Set<FeedEntry> fe = new TreeSet<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				fe.add((FeedEntry) object);
			}
		}

		return fe;
	}

	@Override
	public FeedEntry selectLastFeedEntryByChannel(Channel ch) {

		String query = new String("select fe from FeedEntry fe where fe.channelId=:ch");
		List<?> q = findByQuery(query, "ch", ch.getId());

		Set<FeedEntry> feList = new TreeSet<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				FeedEntry fe = (FeedEntry) object;
				feList.add(fe);
			}
		}

		if (feList.isEmpty()) {
			return null;
		}

		FeedEntry last = Collections.min(feList);/*, new Comparator<FeedEntry>() {
			@Override
			public int compare(FeedEntry o1, FeedEntry o2) {
				return o1.getPublishDate().compareTo(o2.getPublishDate());
			}
		});*/

		return last;
	}

	@Override
	public Set<FeedEntry> findFeedEntrybyChannel(Channel ch) {
		String query = new String("select fe from FeedEntry fe where fe.channelId=:ch");
		List<?> q = findByQuery(query, "ch", ch.getId());

		// List<?> q = em.findByQuery("select fe from FeedEntry fe");

		Set<FeedEntry> feList = new TreeSet<>();
		String chId = ch.getId();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				FeedEntry fe = (FeedEntry) object;
				// String feCh = fe.getChannel().getId();
				// if(chId.equals(feCh)){
				feList.add(fe);
				// }
			}
		}

		return feList;
	}

	@Override
	public Set<FeedEntry> listbyChannelAfterRegistration(Channel ch,Date registrationDate) {
		String query = new String("select fe from FeedEntry fe where fe.channelId = :id and fe.createDate >= :d");
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("id", ch.getId());
		parameters.put("d", registrationDate);
		List<?> q = findByQuery(query, parameters);
		
		Set<FeedEntry> fe = new TreeSet<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				fe.add((FeedEntry) object);
			}
		}
		return fe;
	}

	@Override
	public Set<FeedEntry> findbyChannelAfterRegistration(Channel ch, Date registrationDate, String term) {
		String query = new String("select fe from FeedEntry fe where fe.channelId = :id and fe.createDate >= :d");
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("id", ch.getId());
		parameters.put("d", registrationDate);
		List<?> q = findByQuery(query, parameters);
				
		String searchTerm = term.toLowerCase();
		Set<FeedEntry> feList = new TreeSet<>();
		for (Object object : q) {
			if (object instanceof FeedEntry) {
				FeedEntry fe = (FeedEntry) object;
				if((fe.getContent() != null && fe.getContent().toLowerCase().contains(searchTerm)) || (fe.getTitle() != null && fe.getTitle().toLowerCase().contains(searchTerm))){
					feList.add((FeedEntry) object);
				}
			}
		}
		return feList;
	}
}
