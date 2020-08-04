package edu.txstate.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.txstate.cs.service.UserService;

@RestController
public class PublicRestController {
	
	@Autowired UserService svc;	

	@GetMapping("/api/checkusername/{username}")
	public boolean checkUsername(@PathVariable("username") String username) {
		return svc.checkUsernameAvailability(username);
	}
	
	@GetMapping("/api/checkemail/{email}")
	public boolean checkEmail(@PathVariable("email") String email) {
		return svc.checkEmailAvailability(email);
	}

}
