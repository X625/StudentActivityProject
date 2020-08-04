package edu.txstate.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.model.bto.UserBTO;
import edu.txstate.cs.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService svc;
	
	
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("user", new UserBTO());
		return "login";
	}
	
	
//	@PostMapping("/login")
//	public String login(@ModelAttribute UserBTO bto) {
//		System.out.println("here?");
//		boolean result = svc.authenticate(bto);
//		System.out.println(result);
//		return result ? "redirect:/home" : "login";
//	}

}
