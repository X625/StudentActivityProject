package edu.txstate.cs.model.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import edu.txstate.cs.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Roommate {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private Gender gender;
	private LocalDate availabilityDate;
	private int price;
	
	public Roommate(String name, Gender gender, LocalDate availabilityDate, int price) {
		this.name = name;
		this.gender = gender;
		this.availabilityDate = availabilityDate;
		this.price = price;
	}
	
}
