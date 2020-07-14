package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.txstate.cs.model.dto.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{

    public Department findByName(String name);

}
