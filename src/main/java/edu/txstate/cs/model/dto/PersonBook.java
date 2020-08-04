package edu.txstate.cs.model.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class PersonBook {
	
	@EmbeddedId
	private PersonBookId id = new PersonBookId();


	@MapsId("personId")
	@JoinColumn(name = "PERSON_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private User person;
	
	
    @MapsId("bookId")
    @JoinColumn(name = "BOOK_ID")
    @ManyToOne(fetch = FetchType.LAZY)
	private Book book;
	
	private double paid;
	
	public PersonBook() {}

	public PersonBook(User person, Book book) {
		this.person = person;
		this.book = book;
	}
	

}
