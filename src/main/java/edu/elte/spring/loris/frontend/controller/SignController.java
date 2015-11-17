package edu.elte.spring.loris.frontend.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.service.UserService;
import edu.elte.spring.loris.backend.service.UserServiceImpl;
import edu.elte.spring.loris.backend.util.UserException;
import edu.elte.spring.loris.frontend.model.UserModel;

@Controller
public class SignController {

	private static final Logger logger = LoggerFactory.getLogger(SignController.class);

	UserService uService;

	public SignController() {
		uService = new UserServiceImpl();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		UserModel user = new UserModel();
		model.put("user", user);
		return "sign";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("user") @Valid UserModel user, BindingResult result,
			Map<String, Object> model) {

		if (result.hasErrors()) {
			return "home";
		}

		// User registered = new User();
		User u = new User();
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail());

		logger.info(user.toString());
		try {
			uService.createUser(u);
		} catch (UserException e) {
			e.printStackTrace();
		}
		return "sign";
	}

	/*
	 * public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid
	 * UserDto accountDto, BindingResult result, WebRequest request, Errors
	 * errors) { User registered = new User(); if (!result.hasErrors()) {
	 * registered = createUserAccount(accountDto, result); } if (registered ==
	 * null) { result.rejectValue("email", "message.regError"); } // rest of the
	 * implementation }
	 */

	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public String viewSignIn(Map<String, Object> model) {
		UserModel user = new UserModel();
		model.put("user", user);
		return "sign";
	}

	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String processSignIn(@ModelAttribute("user") UserModel user, Map<String, Object> model) {
		logger.info(user.toString());

		return "sign";
	}

}
