package edu.txstate.cs.controller;

import edu.txstate.cs.model.dto.Department;
import edu.txstate.cs.model.dto.Person;
import edu.txstate.cs.service.DepartmentService;
import edu.txstate.cs.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personservice;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Person>> getPersons()
    {
        return new ResponseEntity<List<Person>>(personservice.getAllPersons(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Person> findByFnameAndLnameAndDepartment(@RequestParam("fname") String fname, @RequestParam("lname") String lname, @RequestParam("department_name") String department_name)
    {
        Department department = departmentService.getDepartmentByName(department_name);
       return personservice.getAllPersonsByFnameAndLnameAndDepartment(fname, lname, department);
    }
}
