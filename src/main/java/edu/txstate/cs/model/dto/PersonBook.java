package edu.txstate.cs.model.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PersonBook {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
	private Person person;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
	private Book book;
		
	private double pricePaid;
	
	private Date purchaseDate;
	

}
