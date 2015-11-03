package edu.elte.spring.loris.frontend.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.FeedEntry;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.FeedEntryServiceImpl;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.frontend.model.ChannelModel;;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	// @Autowired
	private ChannelService channel;
	private FeedEntryService fe;

	HomeController() {
		this.channel = new ChannelServiceImpl();
	}

	@RequestMapping("/showContentPart")
	public String showContentPart() {
	    return "content-part";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		//model.addAttribute("fe",fe.listFeedEntry());

		return "home";
	}

	@ModelAttribute("foo")
	public ChannelModel prepareChannelModel() {
		return new ChannelModel();
	}

	@ModelAttribute("listEntry")
	public List<FeedEntry> listEntry() {
		List<FeedEntry> l = new FeedEntryServiceImpl().listFeedEntry();
		Integer size = l.size();
		return l;
	}

	@RequestMapping(value = "/processForm", method = RequestMethod.POST)
	public String addChannel(@Valid @ModelAttribute(value = "foo") ChannelModel foo, BindingResult result) {

		if (result.hasErrors()) {
			return "home";
		}

		try {
			channel.insertChannel(foo.getLink());
		} catch (IllegalArgumentException | FeedException | IOException e) {
			result.rejectValue("link", "error.link", "Cannot parse this URL.");
			return "home";
		} catch (ChannelException e) {
			result.rejectValue("link", "error.link", String.format("Channel already exists: %s", foo.getLink()));
			return "home";
		}

		logger.info("Inserted channelService: {}.", foo.getLink());

		for (Channel ch : channel.listChannel()) {
			logger.info("Inserted channelService: {}.", ch);
		}

		return "home";
	}
}