package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.txstate.cs.model.dto.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>{

}
