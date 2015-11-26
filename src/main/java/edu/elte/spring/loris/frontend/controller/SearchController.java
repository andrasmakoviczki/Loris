package edu.elte.spring.loris.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.elte.spring.loris.backend.service.SubscriptionService;

@Controller
public class SearchController {

	@Autowired
	SubscriptionService sService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String ListFeedEntry(Model model) {	
		return "search";
	}
}
