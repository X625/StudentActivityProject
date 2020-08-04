package edu.txstate.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.model.bto.UserBTO;
import edu.txstate.cs.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService svc;
	
	
	@GetMapping("/people")
	public String showPersonSearchPage(Model model) {
		model.addAttribute("person", new UserBTO());
		return "people";
	}
	
	@PostMapping("/people")
	public String  searchPeople(@ModelAttribute("person") UserBTO person, Model model){
		String deptName = person.getDepartmentName();
		String fname = person.getFname();
		String lname = person.getLname();
		
		List<UserBTO> result = svc.searchPeople(deptName, fname, lname);
		
		model.addAttribute("people", result);
		return "people";
	}
	
	
	@GetMapping("/profile")
	public String showProfilePage(Model model) {
		UserBTO bto = svc.getProfile();
		model.addAttribute("person", bto);
		return "profile";
	}
	
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("person") UserBTO bto) {
		svc.updateProfile(bto);
		return "redirect:/profile";
	}
	
	
	
}
