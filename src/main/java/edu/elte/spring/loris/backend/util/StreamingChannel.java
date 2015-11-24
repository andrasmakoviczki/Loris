package edu.elte.spring.loris.backend.util;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.service.CategoryService;
import edu.elte.spring.loris.backend.service.CategoryServiceImpl;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.FeedEntryServiceImpl;
import edu.elte.spring.loris.backend.service.SubscriptionService;

public class StreamingChannel {
	private static final Logger logger = LoggerFactory.getLogger(StreamingChannel.class);

	@Autowired
	SubscriptionService sService;
	
	public StreamingChannel() {
	}

	//@Async
	@Scheduled(fixedDelay = 60000)
	public void Streaming(){
		sService.Download();
	}
}
