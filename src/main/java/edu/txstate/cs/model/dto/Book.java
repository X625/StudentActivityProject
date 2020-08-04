package edu.txstate.cs.model.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Book {
	
	@Id
	@GeneratedValue
	@Column(name="BOOK_ID")
	private long id;
	
	private String title;
	
	private String author;
	
	private String isbn;
	
	private String store;
	
	private String library;
	
	private String imageUrl;
	
	private double price;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PersonBook> personBooks;
	
	public Book() {}

	public Book(String title, String author,  String isbn, String imageUrl,
			String library, String store, double price) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.imageUrl = imageUrl;
		this.store = store;
		this.library = library;
		this.price = price;
	}	

}
