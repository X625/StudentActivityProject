package edu.txstate.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.txstate.cs.exception.UserRegisterationException;
import edu.txstate.cs.model.bto.UserBTO;
import edu.txstate.cs.service.UserService;

@Controller
public class RegisterationController {

	@Autowired
	UserService svc;

	@GetMapping("/signup")
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new UserBTO());
		return "signup";
	}

	@PostMapping("/signup")
	public ModelAndView register(@ModelAttribute("user") UserBTO bto) {
		try {
			svc.signup(bto);
		} catch (UserRegisterationException e) {
			ModelAndView mav = new ModelAndView("signup", "user", bto);
            mav.addObject("error", e.getMessage());
            return mav;
		}
		return new ModelAndView("redirect:/login", "user", bto);
	}
}
