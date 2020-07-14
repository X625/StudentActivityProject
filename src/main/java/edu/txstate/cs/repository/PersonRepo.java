package edu.txstate.cs.repository;

import edu.txstate.cs.model.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.txstate.cs.model.dto.Person;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>{

    List<Person> findAllByFnameAndLnameAndDepartment(String fname, String lname, Department department);
}
