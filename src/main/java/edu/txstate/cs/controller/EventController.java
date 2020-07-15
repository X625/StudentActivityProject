package edu.txstate.cs.controller;

import edu.txstate.cs.model.dto.Event;
import edu.txstate.cs.service.EventService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/events")
    public List<Event> listEvents(@RequestBody Map<String, String> params)
    {
        LocalDate before = LocalDate.parse(params.get("before"));
        LocalDate after = LocalDate.parse(params.get("after"));
        return service.getEventsBeforeAndAfter(after, before);
    }
}
