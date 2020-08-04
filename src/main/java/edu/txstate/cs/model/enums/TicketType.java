package edu.txstate.cs.model.enums;

public enum TicketType {
	Zone1(2), Zone2(4), Zone3(6), BusCard(40);
	
	private int price;
	
	public int getPrice() {
		return price;
	}
	
	private TicketType(int price) {
		this.price = price;
	}
}
