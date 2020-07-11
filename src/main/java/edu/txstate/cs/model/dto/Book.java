package edu.txstate.cs.model.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Book {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String title;
	
	@NotNull
	@ElementCollection
	private List<String> authors;
	
	@NotNull
	
	private String isbn;
	
	@NotNull
	private String bookStore;
	
	
	@OneToMany(
	        mappedBy = "book",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<PersonBook> personBookList;
	
	private String libraryLocation;
	
	private double price;

	public Book(@NotNull String title, @NotNull List<String> authors, @NotNull String isbn, @NotNull String bookStore,
			String libraryLocation, double price) {
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.bookStore = bookStore;
		this.libraryLocation = libraryLocation;
		this.price = price;
	}
	
	

}
