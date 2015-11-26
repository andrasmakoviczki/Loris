package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.dao.SubscriptionDaoImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.Topic;
import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.ConnectionCheck;
import edu.elte.spring.loris.backend.util.FeedHandler;
import edu.elte.spring.loris.backend.util.URLNormailze;
import edu.elte.spring.loris.backend.util.exception.ChannelException;
import edu.elte.spring.loris.backend.util.exception.UserException;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	@Autowired
	SubscriptionDaoImpl sDao;
	@Autowired
	UserService uService;
	@Autowired
	ChannelService chService;
	@Autowired
	FeedEntryService feService;
	@Autowired
	TopicService tService;
	@Autowired
	CategoryService caService;

	public SubscriptionServiceImpl() {
	}

	@Override
	public void createSubscription(Subscription s) {
		sDao.insert(s);
	}

	@Override
	public void removeSubscription(Subscription s) {
		sDao.remove(s);
	}

	@Override
	public void createSubscription(String channelUrl) throws UserException, IllegalArgumentException, ChannelException,
			FeedException, IOException, URISyntaxException {

		// Kapott url normailizálása
		URLNormailze urlNorm = new URLNormailze();
		URL url = urlNorm.normailze(channelUrl);

		if (!chService.findChannelbyUrl(url.toString()).isEmpty()) {
			throw new ChannelException(String.format("Channel already exists: %s", channelUrl));
		}

		// Channel építése az RSS ből
		FeedHandler r = new FeedHandler(url);
		Channel ch = new Channel();
		r.ChannelBuild(ch);
		Date createDate = new Date();
		ch.setCreateDate(createDate);
		ch.setLink(url.toString());
		chService.createChannel(ch);

		// Aktuális user kiválasztása
		User u = uService.getCurrentUser();

		// Feliratkozása létrehozása
		Subscription s = new Subscription();
		s.setChannel(ch);
		s.setUser(u);
		sDao.insert(s);

		// RSS feedek letöltése
		getNewFeeds();
	}

	@Override
	public void updateSubscription(Subscription s) {
		sDao.merge(s);
	}

	@Override
	public void removeSubscription(String id) throws UserException {

		// Channelek listázása feliratkozás alapján
		Channel ch = chService.getChannel(id);
		List<Subscription> sListbyChannel = findSubscriptionbyChannel(ch);

		// Aktuális feliratkozás kiválasztása
		User u = uService.getCurrentUser();
		Subscription actS = sDao.getSubscriptionbyChannelandUser(ch, u);

		if (sListbyChannel.size() > 1) {
			// Ha más is feliratkozott a csatornára, akkor csak a feliratkozást
			// törli
			sDao.remove(actS);
		} else if (sListbyChannel.size() == 1) {
			// Ha csak az aktuális user van feliratkozva,
			// akkor törli a csatornához tartozó bejegyzéseket is
			Set<FeedEntry> feList = feService.findFeedEntrybyChannel(ch);
			for (FeedEntry fe : feList) {
				if (fe.getCategory() != null) {
					for (Category ca : fe.getCategory()) {
						caService.removeCategory(ca);
					}
				}
				if (fe.getTopic() != null) {
					for (Topic t : fe.getTopic()) {
						tService.removeTopic(t);
					}
				}
				feService.removeFeedEntry(fe);
			}

			sDao.remove(actS);

			chService.removeChannel(ch);
		}
	}

	@Override
	public Subscription getSubscription(String id) {
		return sDao.getSubscription(id);
	}

	@Override
	public List<Subscription> listSubscription() {
		return sDao.listSubscription();
	}

	@Override
	public List<Subscription> findSubscriptionbyChannel(Channel ch) {
		return sDao.findSubscriptionbyChannel(ch);
	}

	@Override
	public List<Subscription> findSubscriptionbyCurrentUser() throws UserException {
		User u = uService.getCurrentUser();
		return sDao.findSubscriptionbyUser(u);
	}

	@Override
	public List<Channel> findChannelbyCurrentUser() throws UserException {
		User u = uService.getCurrentUser();
		List<Subscription> sList = sDao.findSubscriptionbyUser(u);

		List<Channel> chList = new ArrayList<>();
		for (Subscription s : sList) {
			chList.add(s.getChannel());
		}

		return chList;
	}

	@Override
	public Map<Channel, Set<FeedEntry>> findFeedEntrybyChannel() throws UserException {

		// Aktuális user kiválasztása
		User u = uService.getCurrentUser();

		Map<Channel, Set<FeedEntry>> l = new HashMap<>();
		List<Channel> chList = new ArrayList<>();

		// User felvett csatornáinak listázása
		List<Subscription> sList = sDao.findSubscriptionbyUser(u);
		for (Subscription s : sList) {
			chList.add(s.getChannel());
		}

		// Csatornákról a felhasználóhoz tartozó feed bejegyzések listázása
		for (Channel ch : chList) {
			Set<FeedEntry> felist = feService.findbyChannelAfterRegistration(ch, u.getCreateDate());
			
			if(!felist.isEmpty()){
				l.put(ch, felist);				
			}
		}

		return l;
	}

	@Override
	public void getNewFeeds() throws IllegalArgumentException, MalformedURLException, FeedException, IOException {
		for (Channel ch : chService.listChannel()) {
			
			if(!ConnectionCheck.portIsOpen(ch.getLink(), 2000)){
				logger.warn("Cannot connect to " + ch.getLink());
				continue;
			}
			
			// Letölti a legfrissebb feedeket a csatorna URL-je alapján
			FeedHandler feeder;
			feeder = new FeedHandler(new URL(ch.getLink()));

			// Ha új a csatorna, vagy a csatorna régebben lett frissítve,
			// mint a legújabb feed
			if (ch.getLastUpdate() == null || ch.getPublishDate().before(feeder.getFeed().getPublishedDate())) {

				// frissíti a csatorna dátumait
				ch.setPublishDate(feeder.getFeed().getPublishedDate());
				ch.setLastUpdate(new Date());
				chService.updateChannel(ch);

				// Utolsó bejegyzés kiválsztása
				Date lastEntryDate;
				if (feService.getLastFeedEntrybyChannel(ch) == null) {
					lastEntryDate = new Date(0);
				} else {
					lastEntryDate = feService.getLastFeedEntrybyChannel(ch).getPublishDate();
				}

				// FeedEntryk létrehozása
				List<FeedEntry> cList = feeder.FeedEntryBuild();
				for (FeedEntry fe : cList) {

					if (!lastEntryDate.before(fe.getPublishDate())) {
						break;
					}

					fe.setCreateDate(new Date());
					fe.setChannel(ch);
					fe.setChannelId(ch.getId());
					fe.setLabeled(false);

					for (Category category : fe.getCategory()) {
						caService.createCategory(category);
					}

					feService.createFeedEntry(fe);
				}
			}
		}
	}

	@Override
	public Map<Channel, Set<FeedEntry>> searchFeedEntrybyChannel(String term) throws UserException {
		User u = uService.getCurrentUser();
		Map<Channel, Set<FeedEntry>> l = new HashMap<>();
		List<Channel> chList = new ArrayList<>();
		List<Subscription> sList = sDao.findSubscriptionbyUser(u);

		for (Subscription s : sList) {
			chList.add(s.getChannel());
		}

		for (Channel ch : chList) {
			Set<FeedEntry> felist = feService.findbyChannelAfterRegistration(ch, u.getCreateDate(), term);

			if(!felist.isEmpty()){
				l.put(ch, felist);				
			}
		}

		return l;
	}
}
