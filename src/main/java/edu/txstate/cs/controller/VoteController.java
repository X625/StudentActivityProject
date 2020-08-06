package edu.txstate.cs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.model.bto.CandidateBTO;
import edu.txstate.cs.model.enums.Candidate;
import edu.txstate.cs.service.UserService;

@Controller
public class VoteController {

	@Autowired
	UserService svc;
		
	@GetMapping("/secret")
	public String showSecretPage(Model model) {
		Map<Candidate, Long> pollResult = svc.getVoteResults();
		model.addAttribute("pollResult", pollResult);
		return "secret";
	}
	
//	@PostMapping("/secret")
//	public String vote() {
//		svc.generateNewPoll();
//		return "redirect:/secret";
//	}
//	
	
	
	@GetMapping("/election")
	public String showElectionPage(Model model) {
		
		boolean voted = svc.hasVoted();
		model.addAttribute("voted", voted);
		model.addAttribute("candidate", new CandidateBTO());
		if(voted) {
			Map<Candidate, Long> pollResult = svc.getVoteResults();
			model.addAttribute("pollResult", pollResult);
		}
		
		return "election";
	}
	
	@PostMapping("/election")
	public String vote(@ModelAttribute CandidateBTO candidate) {
		System.out.println(candidate);
		svc.vote(candidate.getCandidate());
		return "redirect:/election";
	}
}
