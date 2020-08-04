package edu.txstate.cs.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;

	private LocalDate date;

	@ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
	private List<User> people;

	public Event() {}

	public Event(String name, LocalDate date) {
		this.name = name;
		this.date = date;
	}
	

}
