package edu.txstate.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import edu.txstate.cs.model.bto.MealBTO;
import edu.txstate.cs.service.UserService;

@Controller
public class MealController {
	
	@Autowired
	UserService svc;

	@GetMapping("/meals")
	public String showMealsPage(@ModelAttribute("error") String error, Model model) {
		MealBTO bto = new MealBTO();
		bto.setIsmonthly(true);
		model.addAttribute("meal",bto);
		model.addAttribute("meals", svc.getAllUserMealPlans());
		return "meals";
	}
	
	@PostMapping("/meals")
	public String purchaseMealPlan(@ModelAttribute("meal") MealBTO bto, RedirectAttributes redir) {
		boolean result = svc.purchaseMealPlan(bto);
		if(!result) {
			 redir.addFlashAttribute("error","Cannot purchase duplicate meal plan");
		}
	    return "redirect:meals";
	}
}
