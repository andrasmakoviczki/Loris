package edu.elte.spring.loris.backend.util;

import java.io.IOException;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.service.SubscriptionService;

public class StreamingChannel {

	@Autowired
	SubscriptionService sService;
	
	public StreamingChannel() {
	}

	@Scheduled(fixedDelay = 60000)
	public void Streaming(){
		try {
			String HBaseConnection = "http://localhost:16020";
			
			if(!ConnectionCheck.portIsOpen(HBaseConnection, 3000)){
				Log.warn("Cannot connect to " + HBaseConnection);
				return;
			}
			sService.getNewFeeds();
		} catch (IllegalArgumentException | FeedException | IOException e) {
			e.printStackTrace();
		}
	}
}
