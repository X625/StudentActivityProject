package edu.txstate.cs.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.helper.Util;
import edu.txstate.cs.model.bto.RoommateBTO;
import edu.txstate.cs.model.enums.Gender;
import edu.txstate.cs.service.RoommateService;

@Controller
public class RoommateController {

	@Autowired
	private RoommateService svc;

	@GetMapping("/roommates")
	public String showRoommatesPage(Model model) {
		model.addAttribute("roommate", new RoommateBTO());
		return "roommates";
	}

	@PostMapping("/roommates")
	public String searchRoommates(@ModelAttribute("roommate") RoommateBTO bto, Model model) {

		System.out.println(bto);
		Gender gender = bto.getGender();
		LocalDate fromDate = StringUtils.isEmpty(bto.getFromDate()) ? null : Util.convertStringToLocalDate(bto.getFromDate());
		LocalDate toDate = StringUtils.isEmpty(bto.getToDate()) ? null : Util.convertStringToLocalDate(bto.getToDate());
		Integer price = bto.getPrice();

		List<RoommateBTO> result = svc.findRoommates(gender, fromDate, toDate, price);
		model.addAttribute("roommates", result);
		return "roommates";
	}

}
