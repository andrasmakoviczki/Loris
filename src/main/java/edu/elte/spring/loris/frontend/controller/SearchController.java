package edu.elte.spring.loris.frontend.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.service.SubscriptionService;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.frontend.model.ChannelModel;
import edu.elte.spring.loris.frontend.model.SearchModel;

@Controller
public class SearchController {

	@Autowired
	SubscriptionService sService;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String ListFeedEntry(Model model) {	
		return "search";
	}
}
