package edu.elte.spring.loris.frontend.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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
import edu.elte.spring.loris.backend.entity.Subscription;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.backend.service.SubscriptionService;
import edu.elte.spring.loris.backend.service.SubscriptionServiceImpl;
import edu.elte.spring.loris.backend.util.ChannelException;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.frontend.model.ChannelModel;

@Controller
public class ChannelController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	ChannelService cService;
	SubscriptionService sService;
		
	public ChannelController() {
		this.cService = new ChannelServiceImpl();
		this.sService = new SubscriptionServiceImpl();
	}
	
	@RequestMapping(value = "/channels", method = RequestMethod.GET)
	public String channels(Model model) {
		//model.addAttribute("serverTime", formattedDate);
		List<Subscription> a = sService.listSubscription();
		/*for (Subscription s : a) {
			logger.info("CurrentUser: ", s.toString());
		}*/
		
		return "channels";
	}
	
	@ModelAttribute("cList")
	public List<Channel> ListChannels() {
		//List<Channel> a  = new ArrayList<>();
		return cService.listChannel();
	}

	@ModelAttribute("cModel")
	public ChannelModel prepareChannelModel() {
		return new ChannelModel();
	}
	
	/*@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteStrategyPage(@RequestParam(value="id", required=true) String id) {
		
		try {
			cService.removeChannel(id);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Deleted");
	 
	    Strategy strategy = strategyService.getStrategy(id);
	    ModelAndView modelAndView = null;
	 
	    if (phase.equals("stage")) {
	        modelAndView = new ModelAndView("strategy-delete");
	        StrategyDTO strategyDTO = StrategyMapper.getDTO(strategy);
	        String message = "Strategy " + strategy.getId() + " queued for display.";
	        modelAndView.addObject("strategyDTO",strategyDTO);
	        modelAndView.addObject("message", message);
	    }
	 
	    if (phase.equals("confirm")) {
	        modelAndView = new ModelAndView("redirect:/strategy/list");
	        strategyService.deleteStrategy(id);
	        String message = "Strategy " + strategy.getId() + " was successfully deleted";
	        modelAndView.addObject("message", message);
	    }
	 
	    if (phase.equals("cancel")) {
	        modelAndView = new ModelAndView("redirect:/strategy/list");
	        String message = "Strategy delete was cancelled.";
	        modelAndView.addObject("message", message);
	    }
	 
	    return "channels";
	}*/

	@RequestMapping(value = "/channels", method = RequestMethod.POST)
	public String addChannel(@Valid @ModelAttribute(value = "cModel") ChannelModel cModel, BindingResult result) {

		/*if (result.hasErrors()) {
			return "channels";
		}*/

		try {
			sService.createSubscription(cModel.getLink());
		} catch (IllegalArgumentException | FeedException | IOException e) {
			result.rejectValue("link", "error.link", "Cannot parse this URL.");
			return "channels";
		} catch (ChannelException e) {
			result.rejectValue("link", "error.link", String.format("Channel already exists: %s", cModel.getLink()));
			return "channels";
		} catch (UserException e) {
			result.rejectValue("link", "error.link", String.format("User not exists"));
		} catch (URISyntaxException e) {
			result.rejectValue("link", "error.link", String.format("User not exists"));;
		}

		logger.info("Inserted channelService: {}.", cModel.toString());

		return "channels";
	}
}
