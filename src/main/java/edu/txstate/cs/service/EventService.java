package edu.txstate.cs.service;

import edu.txstate.cs.model.dto.Event;
import edu.txstate.cs.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepo repo;

    public List<Event> getEventsBeforeAndAfter(LocalDate before, LocalDate after)
    {
        return repo.findByDateBetween(after, before);
    }
}
