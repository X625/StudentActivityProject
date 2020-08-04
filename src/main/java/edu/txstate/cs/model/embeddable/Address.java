package edu.txstate.cs.model.embeddable;

import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

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
