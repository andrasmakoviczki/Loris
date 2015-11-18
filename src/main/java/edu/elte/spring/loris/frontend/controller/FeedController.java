package edu.elte.spring.loris.frontend.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import edu.elte.spring.loris.backend.service.UserService;
import edu.elte.spring.loris.backend.service.UserServiceImpl;
import edu.elte.spring.loris.backend.util.UserException;

@Controller
public class FeedController {
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

	private FeedEntryService feService;
	private ChannelService cService;
	private UserService uService;

	public FeedController() {
		this.feService = new FeedEntryServiceImpl();
		this.cService = new ChannelServiceImpl();
		this.uService = new UserServiceImpl();
	}

	/*@ModelAttribute("feList")
	public List<FeedEntry> ListFeeds() {
		return feService.listFeedEntry();
	}*/

	@ModelAttribute("cList")
	public List<Channel> ListChannels() {
		return cService.listChannel();
	}

	@RequestMapping(value = "/time", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public @ResponseBody String getTime(@RequestParam String name, Model model) {

		List<FeedEntry> fe = feService.findTopic(name, 5);

		String a = "alma";//new String();
		
		List<FeedEntry> f = feService.listFeedEntry(); 
		List<FeedEntry> fen =  f.subList(0, 5);
		FeedEntry fff = feService.findFeedEntry(name);
	
		String b = new String("");
		if (!fe.isEmpty()) {
			Gson g = new Gson();
			 b = g.toJson(fen);

		}

		return b;

		/*
		 * String result = "Time for " + name + " is " + new Date().toString();
		 * List<FeedEntry> f = feService.listFeedEntry(); List<FeedEntry> fe =
		 * f.subList(0, 5);
		 * 
		 * model.addAttribute("alma","korte"); return fe;
		 */
	}

	@RequestMapping(value = "/feed", method = RequestMethod.GET)
	public String showFeed(Model model) {
		
		List<List<FeedEntry>> a = new ArrayList<>();
		try {
			a = feService.findFeedEntrybyUser();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info(Integer.toString(a.size()));

		model.addAttribute("feList", a);

		return "feeds";
	}
}
