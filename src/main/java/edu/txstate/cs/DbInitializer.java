package edu.txstate.cs;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.txstate.cs.model.dto.Book;
import edu.txstate.cs.model.dto.Department;
import edu.txstate.cs.model.dto.Event;
import edu.txstate.cs.model.dto.Meal;
import edu.txstate.cs.model.dto.Person;
import edu.txstate.cs.model.nonedto.EventType;
import edu.txstate.cs.repository.BookRepo;
import edu.txstate.cs.repository.DepartmentRepo;
import edu.txstate.cs.repository.EventRepo;
import edu.txstate.cs.repository.MealRepo;
import edu.txstate.cs.repository.PersonRepo;

@Component
public class DbInitializer implements CommandLineRunner{

	@Autowired
	DepartmentRepo depRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	MealRepo mealRepo;
	
	@Autowired
	EventRepo eventRepo;
	
	
	@Autowired
	PersonRepo personRepo;
	
	@Override
	public void run(String... args) throws Exception {
		initializeDepartments();
		initializeEvents();
		initializeMeals();
		initializeBooks();
		initializePeople();
	}
	
	
	private void initializePeople() {
		// TODO Auto-generated method stub
		
		Department d1 = depRepo.findByName("Computer Science");
		Department d2 = depRepo.findByName("Biology");
		Person p1 = new Person("aidin","aaa","aidinx625","x625@gmail",d1);
		Person p2 = new Person("nagendra","bbb","nagendra", "nagendra@gmail",d1);
		Person p3 = new Person("sivaranjani","ccc","sivaranjani", "sivaranjani@gmail",d1);
		Person p4 = new Person("shishira","ddd", "shishira", "shishira@gmail",d1);
		Person p5 = new Person("aidin","eee","aidin", "aidin@gmail",d2);
		personRepo.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
	}


	private void initializeDepartments() {
		Department d1 = new Department("Computer Science");
		Department d2 = new Department("Biology");
		Department d3 = new Department("Mathmatics");
		Department d4 = new Department("Physics");
		depRepo.saveAll(Arrays.asList(d1,d2,d3,d4));
	}
	
	
	
	private void initializeEvents() {
		Event e1 = new Event("Football match between team X and Y",EventType.SportActivity,LocalDate.of(2020, 8, 1));
		Event e2 = new Event("Graduation Party",EventType.Party,LocalDate.of(2020, 5, 1));
		Event e3 = new Event("4th of July",EventType.Party,LocalDate.of(2020, 7, 4));
		Event e4 = new Event("Basketball match between team A and B",EventType.SportActivity,LocalDate.of(2020, 10, 23));
		Event e5 = new Event("Basketball match between team B and C",EventType.SportActivity,LocalDate.of(2021, 2, 5));
		eventRepo.saveAll(Arrays.asList(e1,e2,e3,e4,e5));
	}
	
	
	private void initializeMeals() {
		Meal m1 = new Meal("lunch package");
		Meal m2 = new Meal("breakfast package");
		Meal m3 = new Meal("dinner package");
		mealRepo.saveAll(Arrays.asList(m1,m2,m3));
	}
	
	
	private void initializeBooks() {
		Book b1 = new Book("Software Engineering (10th Edition)", Arrays.asList("Ian Sommerville"), "0133943038", "Amazon", "A1R3-4", 49.55);
		Book b2 = new Book("The Design of Web APIs", Arrays.asList("Arnaud Lauret"), "1617295108", "Amazon", "B3R1-4", 42.74);
		Book b3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", Arrays.asList("Erich Gamma","Richard Helm","Ralph Johnson","John Vlissides"), "0201633612", "ebay", null, 59.99);
		Book b4 = new Book("Doing Agile Right: Transformation Without Chaos", Arrays.asList("Darrell Rigby","Sarah Elk","Steve Berez"), "163369870X", "Amazon", null, 30);
		Book b5 = new Book("Essential Scrum: A Practical Guide to the Most Popular Agile Process", Arrays.asList("Kenneth S. Rubin"), "0137043295", "ebay", "A1R2-4", 31.72);
		Book b6 = new Book("Succeeding with Agile", Arrays.asList("Mike Cohn"), "0321579364", "Amazon", null, 40.38);
		Book b7 = new Book("Calculus 8th Edition", Arrays.asList("James Stewart"), "1285740629", "Amazon", "B1R1-1", 230.42);
		bookRepo.saveAll(Arrays.asList(b1,b2,b3,b4,b5,b6,b7));
	}

}
