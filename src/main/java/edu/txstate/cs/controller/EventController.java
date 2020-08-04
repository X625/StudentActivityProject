package edu.txstate.cs.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.helper.Util;
import edu.txstate.cs.model.bto.EventBTO;
import edu.txstate.cs.service.EventService;

@Controller
public class EventController {

    @Autowired
    private EventService svc;
    
    
    private void populateModel(Model model) {
    	if(model.getAttribute("event") == null) {
	    	LocalDate today = LocalDate.now();
	    	String formattedDate = today.format(Util.DATE_FORMATTER);
	    	model.addAttribute("event", new EventBTO(formattedDate,formattedDate));
    	}

    	
    	SortedMap<Integer, SortedMap<Integer, List<EventBTO>>> result = svc.getUserEventsMap();
    	model.addAttribute("userevents",result);
    }
    
    @GetMapping("/events")
    public String showEventsPage(Model model) {
    	populateModel(model);
    	return "events";
    }
    
    
    @PostMapping("/events")
    public String searchEvents(@ModelAttribute("event") EventBTO event, Model model) {

    	List<EventBTO> result = svc.getEventsBeforeAndAfter(event.getFrom(), event.getTo());
    	model.addAttribute("events", result);
    	populateModel(model);
    	return "events";
    }
    
    @PostMapping("/enroll/events/{id}")
	public String purchaseEvent(@PathVariable("id") long id, Model model) {
		svc.enrollEvent(id);
		return "redirect:/events";
	}
 
}
