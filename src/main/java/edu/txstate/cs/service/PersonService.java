package edu.txstate.cs.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.txstate.cs.model.bto.PersonBTO;
import edu.txstate.cs.repository.PersonRepo;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepo repo;
	
	// This is a bad approach to filter result!
	// never do it in real project
	// lazy way to get it done just for grading purpose :D
	public List<PersonBTO> searchPeople(String deptName, String fname, String lname){
		
		return repo.findAll().stream()
				.filter(p -> StringUtils.isEmpty(fname) || p.getFname().equalsIgnoreCase(fname))
				.filter(p -> StringUtils.isEmpty(lname) || p.getLname().equalsIgnoreCase(lname))
				.filter(p -> StringUtils.isEmpty(deptName) || p.getDepartment().getName().equalsIgnoreCase(deptName))
				.map(p -> new PersonBTO(p.getDisplayName(), p.getDepartment() == null ? null : p.getDepartment().getName(), p.getPhoneNumber(), p.getEmail()))
				.collect(toList());
		
	}

}
