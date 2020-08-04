package edu.txstate.cs.model.bto;

import edu.txstate.cs.model.enums.TicketType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class TicketBTO {
	
	private TicketType type;
	
	private int quantity;

	public TicketBTO(TicketType type, int quantity) {
		this.type = type;
		this.quantity = quantity;
	}

}
