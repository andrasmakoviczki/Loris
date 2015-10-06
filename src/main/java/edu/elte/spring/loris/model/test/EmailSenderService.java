package edu.elte.spring.loris.model.test;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@ServiceActivator(inputChannel = "busAsync")
	public void send(SendEmailEvent event) {
		//logger.info("Received request to send email.");
		doSendEmailAndStuff(event);
	}

	private void doSendEmailAndStuff(SendEmailEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
	}
}
