package edu.txstate.cs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.txstate.cs.service.UserService;

@Controller
public class ForgotPasswordController {

	@Autowired
	UserService svc;
	
	@GetMapping("/forgot")
	public String showForgotPage(Model model) {
		model.addAttribute("email", "");
		return "forgot";
	}
	
	@PostMapping("/forgot")
	public ModelAndView forgotPassword(HttpServletRequest request, @RequestParam("email") String email, RedirectAttributes redirect) {
		svc.forgotPassword(email);
		redirect.addFlashAttribute("msg","If you provided valid email address, You should receive a password reset email shortly");
		redirect.addFlashAttribute("msgClass","success");
		return new ModelAndView("redirect:/login");
	}
}
