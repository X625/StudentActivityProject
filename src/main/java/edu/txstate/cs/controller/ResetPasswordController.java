package edu.txstate.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.txstate.cs.service.UserService;

@Controller
public class ResetPasswordController {
	
	@Autowired
	UserService svc;
	
	
	@PostMapping("/resetpassword")
	public void resetPassword() {
		
	}
	
	
	@GetMapping("/resetpassword")
	public void showResetPasswordPage(@RequestParam("token") String token) {
		svc.resetPassword(token);
	}

}
