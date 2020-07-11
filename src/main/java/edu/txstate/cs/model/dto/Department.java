package edu.txstate.cs.model.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Getter
@Setter
public class Department {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	private String name;
	
	
	@OneToMany(mappedBy = "department")
	private Set<Person> people;
	
	
	public Department() {}
	
	public Department(String name) {
		this.name = name;
	}
	
	public void addPerson(Person person) {
		if(people == null) {
			people = new HashSet<>();
		}
		
		people.add(person);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
