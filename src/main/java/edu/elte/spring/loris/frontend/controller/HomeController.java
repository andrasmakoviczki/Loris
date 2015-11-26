package edu.elte.spring.loris.frontend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.service.UserService;
import edu.elte.spring.loris.backend.util.exception.UserException;
import edu.elte.spring.loris.frontend.model.UserModel;;

@Controller
public class HomeController {

	@Autowired
	private UserService uService;

	HomeController() {
	}

	@ModelAttribute("user")
	public UserModel prepareUserModel() {
		return new UserModel();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome(Model model) {
		return "home";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("user") @Valid UserModel user, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return "home";
		}

		User u = new User();
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail());
		u.setName(user.getFullname());

		try {
			uService.createUser(u);
		} catch (UserException e) {
			result.reject("home.useralreadyexists");
			return "home";
		}
		return "redirect:/sign";
	}
}