package edu.elte.spring.loris.frontend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.FeedEntryServiceImpl;
import edu.elte.spring.loris.backend.service.SubscriptionService;
import edu.elte.spring.loris.backend.service.UserService;
import edu.elte.spring.loris.backend.service.UserServiceImpl;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.frontend.model.ChannelModel;
import edu.elte.spring.loris.frontend.model.SearchModel;

@Controller
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private FeedEntryService feService;
	private ChannelService cService;
	
	@Autowired
	private SubscriptionService sService;
	@Autowired
	private SubscriptionService tService;

	public NewsController() {
	}
	
	@ModelAttribute("searchModel")
	public SearchModel prepareSearchModel() {
		return new SearchModel();
	}

	@RequestMapping(value = "/topic", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public @ResponseBody String getTime(@RequestParam String name, Model model) {

		Set<FeedEntry> fel = feService.listFeedEntry();
	
		String b = new String("");
		if (!fel.isEmpty()) {
			Gson g = new Gson();
			 b = g.toJson(fel);

		}

		return b;
	}

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String showFeed(Model model) {
		
		Map<Channel, Set<FeedEntry>> a = new HashMap<>();
		try {
			a = sService.listFeedEntrybyChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info(Integer.toString(a.size()));

		model.addAttribute("feList", a);

		return "news";
	}
	
	@RequestMapping(value = "/news/find", method = RequestMethod.GET)
	public String search(@RequestParam(value="term", required=false) String searchResult, Model model){
		Map<Channel,Set<FeedEntry>> feList = new HashMap<>();
		try {
			feList = sService.findFeedEntrybyChannel(searchResult);
		} catch (UserException e) {
			e.printStackTrace();
		}
		
		Integer feSize = 0;
		for (Set<FeedEntry> fe : feList.values()) {
			feSize = feSize + fe.size();
		}
		
		model.addAttribute("feList",feList);
		model.addAttribute("feSize",feSize);

		return "search";
	}
}
