package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.txstate.cs.model.dto.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long>{
	Department findByName(String name);
}
