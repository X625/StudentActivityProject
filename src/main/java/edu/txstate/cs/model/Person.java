package edu.txstate.cs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
	
	@Id
	@GeneratedValue
	private long id;

	
	public Person(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
	private String fname;
	private String lname;

}
