package edu.elte.spring.loris.frontend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.SubscriptionService;
import edu.elte.spring.loris.backend.util.exception.UserException;
import edu.elte.spring.loris.frontend.model.SearchModel;

@Controller
public class NewsController {
	@Autowired
	private FeedEntryService feService;
	@Autowired
	private SubscriptionService sService;

	public NewsController() {
	}

	@ModelAttribute("searchModel")
	public SearchModel prepareSearchModel() {
		return new SearchModel();
	}

	@RequestMapping(value = "/topic", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public @ResponseBody String topicList(@RequestParam String name, Model model) {

		Set<FeedEntry> sameFeeds = feService.findTopic(name, 5);

		String result = new String("");
		if (!sameFeeds.isEmpty()) {
			Gson convert = new Gson();
			result = convert.toJson(sameFeeds);
		}

		return result;
	}

	@ModelAttribute("feList")
	public Map<Channel, Set<FeedEntry>> listFeeds() {
		Map<Channel, Set<FeedEntry>> feList = new HashMap<>();
		try {
			feList = sService.findFeedEntrybyChannel();
		} catch (Exception e) {
			return new HashMap<>();
		}

		return feList;
	}

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String showFeed(Model model) {
		return "news";
	}

	@RequestMapping(value = "/news/find", method = RequestMethod.GET)
	public String search(@RequestParam(value = "term", required = false) String searchResult, Model model) {
		Map<Channel, Set<FeedEntry>> feList = new HashMap<>();
		try {
			feList = sService.searchFeedEntrybyChannel(searchResult);
		} catch (UserException e) {
			return "news";
		}

		Integer feSize = 0;
		for (Set<FeedEntry> fe : feList.values()) {
			feSize = feSize + fe.size();
		}

		model.addAttribute("feList", feList);
		model.addAttribute("feSize", feSize);

		return "search";
	}
}
