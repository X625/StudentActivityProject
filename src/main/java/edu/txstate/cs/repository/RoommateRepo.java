package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import edu.txstate.cs.model.dto.Roommate;

public interface RoommateRepo extends CrudRepository<Roommate, Long>, JpaSpecificationExecutor<Roommate>{

}
