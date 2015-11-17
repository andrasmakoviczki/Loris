package edu.elte.spring.loris.backend.util;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.service.CategoryService;
import edu.elte.spring.loris.backend.service.CategoryServiceImpl;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.FeedEntryServiceImpl;

public class StreamingChannel {
	private static final Logger logger = LoggerFactory.getLogger(StreamingChannel.class);

	ChannelService cService;
	FeedEntryService feService;
	CategoryService caService;

	public StreamingChannel() {
		this.cService = new ChannelServiceImpl();
		this.feService = new FeedEntryServiceImpl();
		this.caService = new CategoryServiceImpl();
	}

	//@Async
	@Scheduled(fixedDelay = 60000)
	public void Streaming() {
		for (Channel ch : cService.listChannel()) {
			rssFeedDownload feeder;
			try {
				feeder = new rssFeedDownload(new URL(ch.getLink()));			
				
				if (ch.getLastUpdate() == null || ch.getPublishDate().before(feeder.getFeed().getPublishedDate())){
					ch.setPublishDate(feeder.getFeed().getPublishedDate());
					ch.setLastUpdate(new Date());
					cService.updateChannel(ch);
					
					//FeedEntryk létrehozása
					Date lastEntryDate;
					if(feService.getLastFeedEntry() == null){
						lastEntryDate = new Date(0);
					}
					else {
						lastEntryDate = feService.getLastFeedEntry().getPublishDate();
					}
					
					List<FeedEntry> cList = feeder.FeedEntryBuild();		
										
					for (FeedEntry fe : cList) {
						if(!lastEntryDate.before(fe.getPublishDate())){
							break;
						}
						
						fe.setCreateDate(new Date());
						fe.setChannel(ch);
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
}
