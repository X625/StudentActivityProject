package edu.txstate.cs.model.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PersonBookId implements Serializable{
	
	private long personId;
	private long bookId;
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PersonBookId that = (PersonBookId) o;
        return Objects.equals(personId, that.personId) && Objects.equals(bookId, that.bookId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(personId, bookId);
    }

}
