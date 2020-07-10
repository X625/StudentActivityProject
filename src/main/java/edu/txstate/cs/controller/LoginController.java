package edu.txstate.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping({"/login","/"})
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegistrationPage() {
		return "register";
	}
}
