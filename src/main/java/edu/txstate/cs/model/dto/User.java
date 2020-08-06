package edu.txstate.cs.model.dto;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import edu.txstate.cs.model.embeddable.Address;
import edu.txstate.cs.model.embeddable.Meal;
import edu.txstate.cs.model.enums.Candidate;
import edu.txstate.cs.model.enums.TicketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email" }))
public class User {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private long id;

	@NotNull
	private String fname;

	@NotNull
	private String lname;

	@Transient
	@Setter(value = AccessLevel.NONE)
	private String displayName;

	@NaturalId
	private String username;

	@NaturalId
	private String email;

	private String password;

	private String department;

	private String phoneNumber;
	
	@Embedded
	private Address address;

	
	@CollectionTable(
			name="PERSON_TICKET",
	        joinColumns=@JoinColumn(name = "USER_ID")
	)
	@MapKeyColumn(name = "TICKET_TYPE")
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "Quantity")
	@ElementCollection
	private Map<TicketType, Integer> tickets;

	@ElementCollection
	@CollectionTable(
			name="PERSON_MEAL",
	        joinColumns=@JoinColumn(name = "PERSON_ID")
	)
	private Set<Meal> meals;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<PersonBook> personBooks;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "PERSON_EVENT", 
    	joinColumns = @JoinColumn(name = "USER_ID"), 
    	inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
	private List<Event> events;
	
	
	@Enumerated(EnumType.STRING)
	private Candidate vote;


	public User() {
	}

	public User(String fname, String lname, String email, String phoneNumber, String department) {
		this.fname = fname;
		this.lname = lname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.department = department;
	}


	
	
	public User(String fname, String lname, String username, String email, int candidate, String department) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.email = email;
		this.vote = Candidate.values()[candidate];
		this.department = department;
	}
	
	public void addPurchasedBook(Book book, double paid) {
		if(this.personBooks == null) {
			this.personBooks = new ArrayList<>();
		}
		
		PersonBook pb = new PersonBook();
		pb.setPerson(this);
		pb.setBook(book);
		pb.setPaid(paid);
		this.personBooks.add(pb);
	}
	

	public String getDisplayName() {
		return this.fname + " " + this.lname;
	}
	
	
	public List<Book> getBooks(){	
		return this.personBooks == null ? null : personBooks.stream()
				.map(pb -> {
					Book b = pb.getBook();
					b.setPrice(pb.getPaid());
					return b;
				})
				.collect(toList());
	}
	
	
	public void addEvent(Event event) {
		if(events == null) {
			events = new ArrayList<>();
		}
		events.add(event);
	}
	
	
	public void addMeal(Meal meal) {
		if(meals == null) {
			meals = new HashSet<>();
		}
		meals.add(meal);
	}
	
	public void addTicket(TicketType t, int qty) {
		if(tickets == null) {
			tickets = new HashMap<>();
		}
		
		int count = tickets.get(t) != null ? tickets.get(t) : 0;
		tickets.put(t,  count + qty);
	}
	
	public boolean hasVoted() {
		return this.vote != null;
	}
	
	public void setVote(Candidate candidate) {
		if(!this.hasVoted()) {
			this.vote = candidate;
		}
	}

	

}
