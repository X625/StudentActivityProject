package edu.txstate.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.txstate.cs.service.UserService;

@Controller
public class HomepageController {

	@Autowired
	UserService svc;
	
	@GetMapping({"/home","/"})
	public String showHomePage(Model model) {
		String displayname = svc.getLoggedInDisplayname();
		model.addAttribute("username", displayname);
		return "home";
	}

}
