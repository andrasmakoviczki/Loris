package edu.elte.spring.loris.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@RequestMapping("/WEB-INF/views/404.html")
	public String showError() {
		return "404";
	}
}
