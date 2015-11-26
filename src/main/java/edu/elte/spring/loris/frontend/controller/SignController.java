package edu.elte.spring.loris.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.elte.spring.loris.backend.service.UserService;
import edu.elte.spring.loris.frontend.model.UserModel;

@Controller
public class SignController {

	@Autowired
	UserService uService;

	public SignController() {
	}

	@ModelAttribute("user")
	public UserModel prepareUserModel() {
		return new UserModel();
	}

	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public String showSignIn(Model model) {
		return "sign";
	}

	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String processSignIn(@ModelAttribute("user") UserModel user, BindingResult result, Model model) {
		return "sign";
	}

}
