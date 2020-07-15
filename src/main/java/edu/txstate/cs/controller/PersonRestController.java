package edu.txstate.cs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.txstate.cs.model.bto.PersonBTO;
import edu.txstate.cs.service.PersonService;


@RestController
public class PersonRestController {
	
	@Autowired
	private PersonService repo;
	
	@PostMapping("/search")
	public List<PersonBTO> searchPeople(@RequestBody Map<String, String> params){
		
		String deptName = params.get("deptName");
		String fname = params.get("fname");
		String lname = params.get("lname");
		
		return repo.searchPeople(deptName, fname, lname);
	}
	
	
	

}
