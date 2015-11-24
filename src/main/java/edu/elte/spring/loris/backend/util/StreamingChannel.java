package edu.elte.spring.loris.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import edu.elte.spring.loris.backend.service.SubscriptionService;

public class StreamingChannel {

	@Autowired
	SubscriptionService sService;
	
	public StreamingChannel() {
	}

	@Scheduled(fixedDelay = 300000)
	public void Streaming(){
		sService.Download();
	}
}
