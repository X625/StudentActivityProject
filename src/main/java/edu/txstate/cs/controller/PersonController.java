package edu.txstate.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.model.bto.PersonBTO;
import edu.txstate.cs.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	PersonService svc;
	
	@GetMapping("/person")
	public String showPersonSearchPage(Model model) {
		model.addAttribute("person", new PersonBTO());
		return "peopleSearch";
	}
	
	
	@PostMapping("/peoplesearch")
	public String  searchPeople(@ModelAttribute("person") PersonBTO person, Model model){
		String deptName = person.getDepartmentName();
		String fname = person.getFname();
		String lname = person.getLname();
		
		List<PersonBTO> result = svc.searchPeople(deptName, fname, lname);
		model.addAttribute("people", result);
		return "peopleSearch";
	}
}
