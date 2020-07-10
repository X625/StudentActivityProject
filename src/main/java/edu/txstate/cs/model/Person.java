package edu.txstate.cs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username","email"}))
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
	private String username;
	private String email;
	private String password;
	
	
	public void SetPassword(String password) {
		this.password = password;
	}
	
}
