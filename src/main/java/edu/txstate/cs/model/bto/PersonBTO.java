package edu.txstate.cs.model.bto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonBTO {
	
	private String displayName;
	private String departmentName;
	private String phoneNumber;
	private String email;

}
