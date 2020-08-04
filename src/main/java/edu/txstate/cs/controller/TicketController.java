package edu.txstate.cs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.model.bto.TBTO;
import edu.txstate.cs.model.bto.TicketBTO;
import edu.txstate.cs.model.enums.TicketType;
import edu.txstate.cs.service.UserService;

@Controller
public class TicketController {
	
	@Autowired
	UserService svc;
	
	@GetMapping("/tickets")
	public String showTicketPage(Model model) {
		Pair<Integer, List<TicketBTO>> result = svc.getUserTickets();
		if(result != null) {
			model.addAttribute("tickets", result.getValue());
			model.addAttribute("total", result.getKey());
		}
		model.addAttribute("tbto", new TBTO());
		return "tickets";
	}
	
	
	@PostMapping("/tickets")
	public String purchaseTicket(@ModelAttribute("tbto") TBTO tbto) {
		Map<TicketType, Integer> tickets = new HashMap<>();
		
		if(tbto.getZ1() > 0) {
			tickets.put(TicketType.Zone1, tbto.getZ1());
		}
		
		if(tbto.getZ2() > 0) {
			tickets.put(TicketType.Zone2, tbto.getZ2());
		}
		
		if(tbto.getZ3() > 0) {
			tickets.put(TicketType.Zone3, tbto.getZ3());
		}
		
		if(tbto.getBs() > 0) {
			tickets.put(TicketType.BusCard, tbto.getBs());
		}
		
		if(!tickets.isEmpty()) {
			svc.purchaseTickets(tickets);
		}
		
		return "redirect:/tickets";
	}

}
