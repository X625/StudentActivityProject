package edu.txstate.cs.helper;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import edu.txstate.cs.model.dto.User;

public class UserSpecification {
	
	public static Specification<User> hasFirstname(String fname){
		if(StringUtils.isEmpty(fname))
			return (user, cq, cb) -> cb.conjunction();
		else
			return (user, cq, cb) -> cb.equal(user.get("fname"), fname);
	}
	
	public static Specification<User> hasLastname(String lname){
		if(StringUtils.isEmpty(lname))
			return (user, cq, cb) -> cb.conjunction();
		else
			return (user, cq, cb) -> cb.equal(user.get("lname"), lname);
	}
	
	public static Specification<User> belongsToDepartment(String departmentName){
		if(StringUtils.isEmpty(departmentName))
			return (user, cq, cb) -> cb.conjunction();
		else
			return (user, cq, cb) -> cb.equal(user.get("departmentName"), departmentName);
	}
}
