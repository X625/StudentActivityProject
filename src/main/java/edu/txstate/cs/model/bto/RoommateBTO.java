package edu.txstate.cs.model.bto;

import edu.txstate.cs.model.enums.Gender;
import lombok.Data;

//@Getter
//@Setter
@Data
public class RoommateBTO {
	
	private String name;
	private String fromDate;
	private String toDate;
	private Gender gender;
	private Integer price;
	private String availability;
	
}
