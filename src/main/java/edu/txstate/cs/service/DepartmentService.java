package edu.txstate.cs.service;

import edu.txstate.cs.model.dto.Department;
import edu.txstate.cs.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo departmentRepo;

    public Department getDepartmentByName(String name)
    {
        return departmentRepo.findByName(name);
    }
}
