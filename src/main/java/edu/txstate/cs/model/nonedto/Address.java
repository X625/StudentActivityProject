package edu.txstate.cs.model.nonedto;

import java.util.List;

import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

import edu.txstate.cs.model.dto.PersonBook;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
	
	@NotNull
	private String street;
	@NotNull
	private String city;
	@NotNull
	private String state;
	@NotNull
	private String zipCode;
}
