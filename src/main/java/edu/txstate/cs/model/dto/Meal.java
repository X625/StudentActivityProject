package edu.txstate.cs.model.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Meal {
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String name;
	
	@OneToMany(
	        mappedBy = "meal",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true,
	        fetch = FetchType.LAZY
	    )
	private List<PersonMeal> personMealList;
	
	private double price;
	
	public Meal() {}
	
	public Meal(String name) {
		this.name = name;
		this.price = 600;
	}
	
	public Meal(String name, double price) {
		this.name = name;
		this.price = price;
	}
}
