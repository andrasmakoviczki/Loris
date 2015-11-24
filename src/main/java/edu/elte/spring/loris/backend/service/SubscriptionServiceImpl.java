package edu.elte.spring.loris.backend.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.dao.SubscriptionDao;
import edu.elte.spring.loris.backend.dao.SubscriptionDaoImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.entity.Topic;
import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.URLNormailze;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.backend.util.rssFeedDownload;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

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
	public void createSubscription(String channelUrl) throws UserException, IllegalArgumentException, ChannelException,
			FeedException, IOException, URISyntaxException {

		User u = uService.getCurrentUser();

		// TODO:Subscription hozza létre a channelt
		if (!chService.findChannelbyUrl(channelUrl).isEmpty()) {
			throw new ChannelException(String.format("Channel already exists: %s", channelUrl));
		}

		Channel ch = new Channel();
		URLNormailze urlNorm = new URLNormailze();

		URL ul = urlNorm.normailze(channelUrl);
		rssFeedDownload r = new rssFeedDownload(ul);
		r.ChannelBuild(ch);

		Date createDate = new Date();
		ch.setCreateDate(createDate);
		ch.setLink(ul.toString());

		chService.createChannel(ch);

		Subscription s = new Subscription();
		s.setChannel(ch);
		s.setUser(u);

		sDao.insert(s);
		Download();
	}

	@Override
	public void removeSubscription(String id) throws UserException {

		Channel ch = chService.findChannel(id);

		List<Subscription> sListbyUser = findSubscriptionbyCurrentUser();
		List<Subscription> sListbyChannel = findSubscriptionbyChannel(ch);
		
		User u = uService.getCurrentUser();
		Subscription actS = sDao.getSubscriptionbyChannelandUser(ch, u);
		
		//TODO: nem elég szép
		// keresse ki a subscription-t és törölje
		
		if (sListbyChannel.size() > 1) {
			sDao.remove(actS);
		} else if (sListbyChannel.size() == 1) {
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
	public Subscription findSubscription(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subscription> listSubscription() {
		return sDao.listSubscription();
	}

	@Override
	public List<Subscription> findSubscriptionbyChannel(Channel ch) {
		return sDao.listSubscriptionbyChannel(ch);
	}

	@Override
	public List<Subscription> findSubscriptionbyCurrentUser() throws UserException {
		User u = uService.getCurrentUser();

		if (u == null) {
			u = new User();
		}

		return sDao.listSubscriptionbyUser(u);
	}

	@Override
	public List<Channel> listChannelbyCurrentUser() throws UserException {
		User u = uService.getCurrentUser();
		List<Subscription> sList = sDao.listSubscriptionbyUser(u);
		List<Channel> chList = new ArrayList<>();
		for (Subscription s : sList) {
			chList.add(s.getChannel());
		}

		return chList;
	}

	@Override
	public Map<Channel, Set<FeedEntry>> listFeedEntrybyChannel() throws UserException {
		User u = uService.getCurrentUser();
		Map<Channel, Set<FeedEntry>> l = new HashMap<>();
		List<Channel> chList = new ArrayList<>();
		List<Subscription> sList = sDao.listSubscriptionbyUser(u);

		for (Subscription s : sList) {
			chList.add(s.getChannel());
		}

		for (Channel ch : chList) {
			Set<FeedEntry> felist = feService.listbyChannelAfterRegistration(ch,u.getCreateDate());
			
			l.put(ch, felist);
		}

		return l;
	}

	@Override
	public void Download() {
			//minden csatornán
			for (Channel ch : chService.listChannel()) {
				List<Subscription> sList = findSubscriptionbyChannel(ch);
				//Ha új csatornát jegyeztünk be
				/*if(sList == null){
					
				}*/
				
				rssFeedDownload feeder;
				try {
					//letölti a legfrissebb feedeket
					feeder = new rssFeedDownload(new URL(ch.getLink()));			
					
					//ha új csatorna, vagy a csatorna régebben lett frissítve, mint a legújabb feed
					if (ch.getLastUpdate() == null || ch.getPublishDate().before(feeder.getFeed().getPublishedDate())){
						//frissíti a csatorna dátumait
						ch.setPublishDate(feeder.getFeed().getPublishedDate());
						ch.setLastUpdate(new Date());
						chService.updateChannel(ch);
						
						//Mikori az utolsó bejegyzés
						Date lastEntryDate;
						if(feService.getLastFeedEntrybyChannel(ch) == null){
							lastEntryDate = new Date(0);
						}
						else {
							lastEntryDate = feService.getLastFeedEntrybyChannel(ch).getPublishDate();
						}
						
						//FeedEntryk létrehozása
						List<FeedEntry> cList = feeder.FeedEntryBuild();		
											
						for (FeedEntry fe : cList) {
							//Ha nincs új bejegyzés
							if(!lastEntryDate.before(fe.getPublishDate())){
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
				} catch (IllegalArgumentException | FeedException | IOException e) {
					e.printStackTrace();
				}
			}		
	}

	@Override
	public Map<Channel, Set<FeedEntry>> findFeedEntrybyChannel(String term) throws UserException {
		User u = uService.getCurrentUser();
		Map<Channel, Set<FeedEntry>> l = new HashMap<>();
		List<Channel> chList = new ArrayList<>();
		List<Subscription> sList = sDao.listSubscriptionbyUser(u);

		for (Subscription s : sList) {
			chList.add(s.getChannel());
		}

		for (Channel ch : chList) {
			Set<FeedEntry> felist = feService.findbyChannelAfterRegistration(ch,u.getCreateDate(),term);
			
			l.put(ch, felist);
		}

		return l;
	}

}
