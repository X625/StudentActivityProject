package edu.txstate.cs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.txstate.cs.model.dto.Event;

public interface EventRepo extends CrudRepository<Event, Long>{

     List<Event> findByDateBetween(LocalDate after, LocalDate before);

}
