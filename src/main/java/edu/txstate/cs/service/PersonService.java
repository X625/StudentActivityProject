package edu.txstate.cs.service;

import edu.txstate.cs.model.dto.Department;
import edu.txstate.cs.model.dto.Person;
import edu.txstate.cs.repository.DepartmentRepo;
import edu.txstate.cs.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepo repo;


    public List<Person> getAllPersons()
    {
        List<Person> persons = new ArrayList<Person>();
        persons = repo.findAll();

        return persons;
    }

    public List<Person> getAllPersonsByFnameAndLnameAndDepartment(String fname, String lname, Department department)
    {
        return repo.findAllByFnameAndLnameAndDepartment(fname, lname, department);
    }

}
