package edu.elte.spring.loris;

//import java.applet.AppletContext;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.elte.spring.loris.backend.dao.HBaseEntityManager;
import edu.elte.spring.loris.backend.dao.HbaseEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.entity.UserHBase;
import edu.elte.spring.loris.backend.service.ChannelService;
import edu.elte.spring.loris.backend.service.ChannelServiceImpl;
import edu.elte.spring.loris.model.HttpGetter;
//import edu.elte.spring.loris.model.PrintTask;
import edu.elte.spring.loris.model.rssStream.GetContent;
import edu.elte.spring.loris.model.rssStream.rssFeedDownload;
import edu.elte.spring.loris.model.test.SendEmailEvent;
import edu.elte.spring.loris.model.test.EmailSenderService;
import edu.elte.spring.loris.model.test.BusGateway;;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	// @Autowired
	private ChannelServiceImpl channel;

	HomeController() {
		this.channel = new ChannelServiceImpl();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		/*Foo foo = new Foo();
		foo.setBar("bar");*/
		
		Channel foo = new Channel();

		model.addAttribute("foo", foo);

		return "home";
	}

	@RequestMapping(value = "/processForm", method = RequestMethod.POST)
	public String addChannel(@ModelAttribute(value = "foo") Channel foo) {

		String chUrl = new String("alma");
		channel.insertChannel(chUrl);
		logger.info("Inserted channel: {}.", chUrl);
		logger.info("Inserted channel: {}.", channel.findChannel("1"));

		return "home";
	}
}