package edu.txstate.cs.helper;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import edu.txstate.cs.model.dto.Roommate;
import edu.txstate.cs.model.enums.Gender;

public class RoommateSpecification {
	
	public static Specification<Roommate> hasGenderOf(Gender gender){
		if (gender != Gender.Unspecified)
			return (roommate, cq, cb) -> cb.equal(roommate.get("gender"), gender);
		else 
			return (roommate, cq, cb) -> cb.conjunction();
	}
	
	
	public static Specification<Roommate> priceBetween(Integer from, Integer to){
		if(from != null && to != null)
			return (roommate, cq, cb) -> cb.between(roommate.get("price"), from, to);
		else if(from == null && to != null)
			return (roommate, cq, cb) -> cb.lessThanOrEqualTo(roommate.get("price"), to);
		else if(from != null && to == null)
			return (roommate, cq, cb) -> cb.greaterThanOrEqualTo(roommate.get("price"), from);
		
		return (roommate, cq, cb) -> cb.conjunction();
	}
	
	
	public static Specification<Roommate> availabilityDateBetween(LocalDate from, LocalDate to){
		if(from != null && to != null)
			return (roommate, cq, cb) -> cb.between(roommate.get("availabilityDate"), from, to);
		else if(from == null && to != null)
			return (roommate, cq, cb) -> cb.lessThanOrEqualTo(roommate.get("availabilityDate"), to);
		else if(from != null && to == null)
			return (roommate, cq, cb) -> cb.greaterThanOrEqualTo(roommate.get("availabilityDate"), from);
		
		return (roommate, cq, cb) -> cb.conjunction();
	}

}
