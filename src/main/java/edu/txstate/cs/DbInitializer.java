package edu.txstate.cs;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.txstate.cs.exception.UserRegisterationException;
import edu.txstate.cs.model.bto.UserBTO;
import edu.txstate.cs.model.dto.Book;
import edu.txstate.cs.model.dto.Event;
import edu.txstate.cs.model.dto.Roommate;
import edu.txstate.cs.model.enums.Gender;
import edu.txstate.cs.repository.BookRepo;
import edu.txstate.cs.repository.EventRepo;
import edu.txstate.cs.repository.RoommateRepo;
import edu.txstate.cs.service.UserService;

@Component
public class DbInitializer implements CommandLineRunner {

	@Autowired
	UserService svc;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	RoommateRepo roommateRepo;
	
	@Autowired
	EventRepo eventRepo;

	String dates ="2020-10-18,2020-11-24,2020-12-04,2020-10-22,2020-12-08,2020-08-29,2020-11-26,2020-08-15,2020-11-02,2020-10-29,2020-11-09,2020-11-14,2020-08-31,2020-09-25,2020-09-03,2020-10-16,2020-12-22,2020-12-09,2020-09-10,2020-08-12";
	
	@Override
	public void run(String... args) throws Exception {
		 initializePeople();
		 initializeEvents();
		 initializeBooks();
		 initializeRoommates();
		
	}

	private void initializePeople() throws UserRegisterationException {
		UserBTO bto = new UserBTO();
		bto.setFname("aidin");
		bto.setLname("hagh");
		bto.setUsername("aidinx625");
		bto.setEmail("aidinx625@gmail.com");
		bto.setPassword("123");
		svc.signup(bto);
	}

	private void initializeEvents() {

		List<String> list = Arrays.asList(
			"Alkek One: Image Manipulation",
			"Alkek One: Introduction to StoryMapping",
			"Faculty Focus Presents: \"Study in America\"",
			"Alkek One: Introduction to 3D Design",
			"Alkek One: Building Your Brand",
			"Alkek One: Introduction to Video Editing",
			"Texas State Women's Soccer vs Stephen F. Austin",
			"Texas State Football vs SMU",
			"Texas State Women's Soccer at Houston Baptist",
			"Texas State Women's Soccer vs Northwestern State",
			"Texas State Football vs UTSA",
			"Texas State Women's Soccer vs UTEP",
			"Texas State Football at ULM",
			"5th Annual Space Settlement Symposium",
			"Texas State Women's Soccer at Little Rock",
			"Diversity Week",
			"Ronald Rael",
			"Business Leadership Week 2021",
			"Spring Commencement",
			"Mass Comm Week");
		
		Set<Event> events = new HashSet<>();
		String[] datearr = dates.split(",");
		Random rand = new Random();
		for(String s: list) {
			int index = rand.nextInt(datearr.length);
			events.add(new Event(s, LocalDate.parse(datearr[index])));
		}
		
		eventRepo.saveAll(events);
	}

	private void initializeBooks() {
		

		bookRepo.saveAll(Arrays.asList(
				new Book("In Search of Lost Time","Marcel Proust","0142437964","/images/books/1.png","Aisle A - Row 3","Amazon",29.99),
				new Book("Ulysses","James Joyce","1840226358","/images/books/2.png",null,"Amazon",33.25),
				new Book("Don Quixote","Miguel de Cervantes","1298827183","/images/books/3.png",null,"Ebay",15),
				new Book("The Great Gatsby","F. Scott Fitzgerald","9780743273565","/images/books/4.png","Aisle B - Row 1","University Bookstore",8.99),
				new Book("One Hundred Years of Solitude","Gabriel Garcia Marquez","0060883286","/images/books/5.png",null,"Amazon",98.99),
				new Book("Moby Dick","Herman Melville","1853260088","/images/books/6.png",null,"University Bookstore",63.99),
				new Book("War and Peace","Leo Tolstoy","1400079985","/images/books/7.png",null,"University Bookstore",74),
				new Book("Lolita","Vladimir Nabokov","9780679723165","/images/books/8.png","Aisle A - Row 1","Amazon",25),
				new Book("Hamlet","William Shakespeare","1450539726","/images/books/9.png",null,"University Bookstore",23),
				new Book("The Catcher in the Rye","J. D. Salinger","7543321726","/images/books/10.png",null,"University Bookstore",90),
				new Book("The Odyssey","Homer","0393089053","/images/books/11.png",null,"Amazon",120),
				new Book("The Brothers Karamazov","Fyodor Dostoyevsky","0374528373","/images/books/12.png",null,"Ebay",83),
				new Book("Crime and Punishment","Fyodor Dostoyevsky","0486415872","/images/books/13.png",null,"University Bookstore",44),
				new Book("Madame Bovary","Gustave Flaubert","9780679736363","/images/books/14.png","Aisle B - Row 2","University Bookstore",51),
				new Book("The Divine Comedy","Dante Alighieri","1420951661","/images/books/15.png",null,"Amazon",34.99),
				new Book("Software Engineering (10th Edition)", "Ian Sommerville", "0133943038","/images/books/16.png",null,"Ebay",35.99),
				new Book("The Design of Web APIs", "Arnaud Lauret", "1617295108","/images/books/17.png",null,"Amazon",11.99),
				new Book("Design Patterns: Elements of Reusable Object-Oriented Software","Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "0201633612","/images/books/18.png",null,"Amazon",90),
				new Book("Doing Agile Right: Transformation Without Chaos", "Darrell Rigby, Sarah Elk, Steve Berez","163369870X","/images/books/19.png","Aisle A - Row 6","University Bookstore",45),
				new Book("Essential Scrum: A Practical Guide to the Most Popular Agile Process", "Kenneth S. Rubin","0137043295","/images/books/20.png",null,"Amazon",70),
				new Book("Succeeding with Agile", "Mike Cohn", "0321579364","/images/books/21.png",null,"Amazon",11),
				new Book("Calculus 8th Edition", "James Stewart", "1285740629","/images/books/22.png","Aisle A - Row 5","Ebay",45)
		));
		
		
		
	}

	private void initializeRoommates() {
		
		String females = "Abby,Lacey,Lucia,Felix,Anisa,Jessie,Amber,Lisa,Sylvia,Maria";
		String males="Ioan,Theo,Stephen,Chris,Dominic,Ryan,Tom,Jack,John, James";
		
		String[] datearr = dates.split(",");
		
		
		Set<Roommate> roommates = new HashSet<>();
		Random rand = new Random();
		for(String name: females.split(",")) {
			int index = rand.nextInt(datearr.length);
			int price = rand.nextInt(4637)+789;
			roommates.add(new Roommate(name,Gender.Female,LocalDate.parse(datearr[index]), price));
		}
		for(String name: males.split(",")) {
			int index = rand.nextInt(datearr.length);
			int price = rand.nextInt(4637)+789;
			roommates.add(new Roommate(name,Gender.Male,LocalDate.parse(datearr[index]), price));
		}
		roommateRepo.saveAll(roommates);
		
		
	}

}
