package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.txstate.cs.model.dto.Event;

public interface EventRepo extends JpaRepository<Event, Long>{

}
