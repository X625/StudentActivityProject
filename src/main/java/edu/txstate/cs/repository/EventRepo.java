package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.txstate.cs.model.dto.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long>{

    public List<Event> findByDateBetween(LocalDate after, LocalDate before);

}
