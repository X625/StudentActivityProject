package edu.txstate.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.txstate.cs.model.bto.UserBTO;
import edu.txstate.cs.service.UserService;

@Controller
public class ResetPasswordController {
	
	@Autowired
	UserService svc;
	
	
	@GetMapping("/resetpassword")
	public ModelAndView showResetPasswordPage(@RequestParam("token") String token, RedirectAttributes redirect) {
		boolean isValid = svc.isResetPasswordTokenValid(token);
		if(isValid) {
			ModelAndView mv = new ModelAndView("resetpassword");
			mv.addObject("user", new UserBTO());
			return mv;
		}
		redirect.addFlashAttribute("msg","Your token is invalid or expired.");
		redirect.addFlashAttribute("msgClass","error");
		return new ModelAndView("redirect:/login");
	}
	
	
	@PostMapping("/resetpassword")
	public ModelAndView resetPassword(@ModelAttribute("user") UserBTO user, RedirectAttributes redirect) {
		svc.resetPasssword(user.getPassword());
		redirect.addFlashAttribute("msg","Your password has been reseted successfully.");
		redirect.addFlashAttribute("msgClass","success");
		return new ModelAndView("redirect:/login");
	}
	
}
