package edu.txstate.cs.model.bto;

import edu.txstate.cs.model.embeddable.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@PasswordMatchesValidator
public class UserBTO {
	
	private String fname;
	private String lname;
	private String displayName;
	private String departmentName;
	private String phoneNumber;
	
//	@EmailValidator
	private String email;
	
	private String username;
	private String password;
	private String cpassword;
	private Address address;
	
	public UserBTO(String displayName, String departmentName, String phoneNumber, String email) {
		this.displayName = displayName;
		this.departmentName = departmentName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	

}
