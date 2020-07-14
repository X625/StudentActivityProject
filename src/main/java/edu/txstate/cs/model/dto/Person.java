package edu.txstate.cs.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NaturalId;

import edu.txstate.cs.model.nonedto.Address;
import edu.txstate.cs.model.nonedto.Gender;
import edu.txstate.cs.model.nonedto.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email" }))
public class Person {

	@Id
	@GeneratedValue
	private long id;
	

	@NotNull
	private String fname;
	
	@NotNull
	private String lname;
	
	@NaturalId
	private String username;
	
	@NaturalId
	private String email;

	private String password;

	private Gender gender = Gender.Unknown;

	@ManyToOne
	@JoinColumn(name="department_id")
	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
	private Department department;

	private String phoneNumber;

	@Embedded
	private Address address;

	private UserRole role = UserRole.Student;

	@ManyToMany
	@JoinTable(name = "person_event", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	private Set<Event> events;
	
	
	
	@OneToMany(
	        mappedBy = "person",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<PersonBook> personBookList;
	
	
	
	@OneToMany(
	        mappedBy = "person",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<PersonMeal> personMealList;
	
	
	public Person() {}

	public Person(String fname, String lname, String username, String email) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Person other = (Person) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


}
