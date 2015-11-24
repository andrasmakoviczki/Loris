package edu.elte.spring.loris.frontend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@RequestMapping("/WEB-INF/views/404.html")
	public String error(HttpServletRequest request, Model model) {
		return "404";
	}
}
