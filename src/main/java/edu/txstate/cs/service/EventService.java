package edu.txstate.cs.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.txstate.cs.helper.Util;
import edu.txstate.cs.model.bto.EventBTO;
import edu.txstate.cs.model.dto.Event;
import edu.txstate.cs.model.dto.User;
import edu.txstate.cs.repository.EventRepo;
import edu.txstate.cs.repository.UserRepo;

@Service
public class EventService {
	
    @Autowired
    private EventRepo erepo;
    
    @Autowired
    private UserRepo prepo;

    public List<EventBTO> getEventsBeforeAndAfter(String from, String to){
    	final List<EventBTO> userEvents = getUserEvents();
    	LocalDate fromDate = Util.convertStringToLocalDate(from);
    	LocalDate toDate = Util.convertStringToLocalDate(to);
        List<Event> list = erepo.findByDateBetween(fromDate, toDate);
        return list == null ? null :  list.stream()
        									.sorted((e1,e2) -> e1.getDate().compareTo(e2.getDate()))
        									.map(Util::mapToEventBTO)
        									.map(bto -> { 
        										if(userEvents.contains(bto)) {
        											bto.setEnrolled(true);
        										}
        										return bto;
        									})
        									.collect(toList());        	
    }

	public void enrollEvent(long id) {
		Event e = erepo.findById(id).orElse(null);
		if(e != null) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User p = prepo.findByUsername(username);
			p.addEvent(e);
			prepo.save(p);
		}		
	}

	public List<EventBTO> getUserEvents() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User p = prepo.findByUsername(username);
		return p.getEvents().stream().map(Util::mapToEventBTO)
							  .sorted((o1,o2) -> o1.getDate().compareTo(o2.getDate()))
							  .collect(toList());
	}
	
	public SortedMap<Integer, SortedMap<Integer, List<EventBTO>>> getUserEventsMap() {
		final List<EventBTO> userEvents = getUserEvents();
		SortedMap<Integer, SortedMap<Integer, List<EventBTO>>> result = new TreeMap<>(); 
		
		for(EventBTO bto: userEvents) {
			if(result.containsKey(bto.getYear())) {
				SortedMap<Integer, List<EventBTO>> map = result.get(bto.getYear());
				if(map.containsKey(bto.getMonth())) {
					map.get(bto.getMonth()).add(bto);
				} else {
					List<EventBTO> list = new ArrayList<>();
					list.add(bto);
					map.put(bto.getMonth(), list);
				}
				
			} else {
				SortedMap<Integer, List<EventBTO>> map = new TreeMap<Integer, List<EventBTO>>();
				List<EventBTO> list = new ArrayList<>();
				list.add(bto);
				map.put(bto.getMonth(), list);
				result.put(bto.getYear(), map);
			}
		}
		
		return result;
	}
    
}