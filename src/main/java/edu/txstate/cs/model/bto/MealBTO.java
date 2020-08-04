package edu.txstate.cs.model.bto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MealBTO {
	
	private String name;
	private int price;
	
	private boolean ismonthly;
	
	private String monthlydate;

	private String sem;
	private String semyear;
	
	public MealBTO(String name, int price) {
		this.name = name;
		this.price = price;
	}
	

}
