package edu.txstate.cs.model.nonedto;

public enum BusTicket {
	
	Zone1(2), Zone2(4), Zone3(6), BusCard(40);
	
	private int price;
		
	private BusTicket(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return this.price;
	}

}
