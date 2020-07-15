package edu.txstate.cs.model.bto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonBTO {
	
	private String fname;
	private String lname;
	private String displayName;
	private String departmentName;
	private String phoneNumber;
	private String email;
	
	public PersonBTO(String displayName, String departmentName, String phoneNumber, String email) {
		this.displayName = displayName;
		this.departmentName = departmentName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	

}
