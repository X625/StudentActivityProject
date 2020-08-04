package edu.txstate.cs.model.bto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventBTO {
	
	
	private long id;
	private String from;
	private String to;
	private String name;
	private LocalDate date;
	private Integer year;
	private Integer month;
	
	private boolean enrolled;
	
	public EventBTO() {}

	public EventBTO(String from, String to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventBTO other = (EventBTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
