package edu.elte.spring.loris.frontend.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.backend.service.FeedEntryService;
import edu.elte.spring.loris.backend.service.FeedEntryServiceImpl;
import edu.elte.spring.loris.backend.service.UserService;
import edu.elte.spring.loris.backend.service.UserServiceImpl;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.frontend.model.UserModel;
import trash.GetService;;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService uService;

	HomeController() {
		this.uService = new UserServiceImpl();
	}

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// public String home(Locale locale, Model model) {
	// logger.info("Welcome home! The client locale is {}.", locale);
	//
	// Date date = new Date();
	// DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
	// DateFormat.LONG, locale);
	//
	// String formattedDate = dateFormat.format(date);
	//
	// model.addAttribute("serverTime", formattedDate);
	// //model.addAttribute("fe",fe.listFeedEntry());
	//
	// return "home";
	// }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		UserModel user = new UserModel();
		model.put("user", user);
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("user") @Valid UserModel user, BindingResult result,
			Map<String, Object> model) {

		if (result.hasErrors()) {
			return "home";
		}
		// TODO
		// User registered = new User();
		User u = new User();
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail());
		u.setName(user.getFullname());

		logger.info(user.toString());
		try {
			uService.createUser(u);
		} catch (UserException e) {
			e.printStackTrace();
		}
		return "sign";
	}

	/*
	 * @ModelAttribute("listEntry") public List<FeedEntry> listEntry() {
	 * List<FeedEntry> l = new ArrayList<>(); //feService.listFeedEntry();
	 * return l; }
	 */
}