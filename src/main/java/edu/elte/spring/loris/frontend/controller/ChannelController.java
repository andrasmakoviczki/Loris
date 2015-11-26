package edu.elte.spring.loris.frontend.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.syndication.io.FeedException;

import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.service.SubscriptionService;
import edu.elte.spring.loris.backend.util.exception.ChannelException;
import edu.elte.spring.loris.backend.util.exception.UserException;
import edu.elte.spring.loris.frontend.model.ChannelModel;

@Controller
public class ChannelController {

	@Autowired
	SubscriptionService sService;

	public ChannelController() {
	}

	@RequestMapping(value = "/channels", method = RequestMethod.GET)
	public String showChannels(Model model) {
		return "channels";
	}

	@ModelAttribute("cList")
	public List<Channel> ListChannels() {
		List<Channel> cList = new ArrayList<>();
		try {
			cList = sService.findChannelbyCurrentUser();
		} catch (UserException e) {
			return new ArrayList<>();
		}

		return cList;
	}

	@ModelAttribute("cModel")
	public ChannelModel prepareChannelModel() {
		return new ChannelModel();
	}

	@RequestMapping(value = "/channels/newChannel", method = RequestMethod.POST)
	public String addChannel(@ModelAttribute("cModel") ChannelModel channel, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "channels";
		}

		try {
			sService.createSubscription(channel.getLink());
		} catch (IllegalArgumentException | FeedException | IOException | URISyntaxException e) {
			result.reject("channels.url");
			return "channels";
		} catch (ChannelException e) {
			result.reject("channels.channelalreadyexists");
			return "channels";
		} catch (UserException e) {
			result.reject("channels.usernotexits");
			return "channels";
		}

		return "redirect:/channels";
	}

	@RequestMapping(value = "/channels/delete", method = RequestMethod.GET)
	public String deleteChannel(@RequestParam(value = "id", required = true) String id) {
		try {
			sService.removeSubscription(id);
		} catch (UserException e) {
			return "channels";
		}

		return "redirect:/channels";
	}
}
