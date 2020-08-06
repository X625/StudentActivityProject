package edu.txstate.cs.controller;

import java.util.Set;

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
		Set<MealBTO> meals = svc.getAllUserMealPlans();
		if(meals != null) {
			int total = meals.stream().mapToInt(MealBTO::getPrice).sum();
			model.addAttribute("total", total);
		}
		
		model.addAttribute("meals", meals);
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
