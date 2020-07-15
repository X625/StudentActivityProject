package edu.txstate.cs.model.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.NotNull;

import edu.txstate.cs.model.nonedto.MealPurchaseType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PersonMeal {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mealId")
	private Meal meal;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
	private Person person;
	
	@NotNull
	private MealPurchaseType purchaseType;

	private double price;


	private PersonMeal() {
	}

	public PersonMeal(Person person, Meal meal, MealPurchaseType purchaseType) {
		this.person = person;
		this.meal = meal;
		this.purchaseType = purchaseType;
		if (purchaseType == MealPurchaseType.ByMonth) {
			this.price = meal.getPrice();
		} else {
			this.price = meal.getPrice() * 95 / 100;
		}
	}

}
