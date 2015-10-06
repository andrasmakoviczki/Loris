package edu.elte.spring.loris.model.test;

import org.springframework.integration.annotation.Gateway;

public interface BusGateway {
	  @Gateway(requestChannel="bus")
	  public void onDaBus(Object foo);
	}
