package edu.txstate.cs.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import edu.txstate.cs.model.bto.BookBTO;
import edu.txstate.cs.model.bto.EventBTO;
import edu.txstate.cs.model.bto.MealBTO;
import edu.txstate.cs.model.bto.UserBTO;
import edu.txstate.cs.model.bto.RoommateBTO;
import edu.txstate.cs.model.dto.Book;
import edu.txstate.cs.model.dto.Event;
import edu.txstate.cs.model.dto.User;
import edu.txstate.cs.model.embeddable.Meal;
import edu.txstate.cs.model.dto.Roommate;



public class Util {
	

	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	public static String convertLocalDateToString(LocalDate date) {
		return date.format(DATE_FORMATTER);
	}
	
	public static LocalDate convertStringToLocalDate(String date) {
		return LocalDate.parse(date, DATE_FORMATTER);
	}
	
	public static UserBTO mapToUserBTO(User person) {
		UserBTO bto = new UserBTO();
		bto.setFname(person.getFname());
		bto.setLname(person.getLname());
		bto.setDepartmentName(person.getDepartment());
		bto.setDisplayName(person.getDisplayName());
		bto.setEmail(person.getEmail());
		bto.setPhoneNumber(person.getPhoneNumber());
		bto.setAddress(person.getAddress());
		bto.setUsername(person.getUsername());
		return bto;
	}
	
	
	public static BookBTO mapToBookBTO(Book book) {
		BookBTO bto = new BookBTO();
		bto.setAuthor(book.getAuthor());
		bto.setId(book.getId());
		bto.setIsbn(book.getIsbn());
		bto.setLibrary(book.getLibrary());
		bto.setPrice(book.getPrice());
		bto.setStore(book.getStore());
		bto.setTitle(book.getTitle());
		bto.setImage(book.getImageUrl());
		return bto;
	}
	
	
	public static EventBTO mapToEventBTO(Event event) {
		EventBTO bto = new EventBTO();
		bto.setName(event.getName());
		bto.setDate(event.getDate());
		bto.setFrom(event.getDate().format(DATE_FORMATTER));
		bto.setTo(bto.getFrom());
		bto.setId(event.getId());
		bto.setYear(event.getDate().getYear());
		bto.setMonth(event.getDate().getMonthValue());
		return bto;
	}
	
	public static RoommateBTO mapToRoommateBTO(Roommate dto) {
		RoommateBTO bto = new RoommateBTO();
		bto.setName(dto.getName());
		bto.setAvailability(Util.convertLocalDateToString(dto.getAvailabilityDate()));
		bto.setGender(dto.getGender());
		bto.setPrice(dto.getPrice());
		return bto;
	}
	
	public static Meal mapToMeal(MealBTO bto) {
		String name = bto.isIsmonthly() ? bto.getMonthlydate() : bto.getSem()+" "+bto.getSemyear();
		int price = bto.isIsmonthly()? 600 : 570;
		return new Meal(name, price);
	}

	public static String encodePassword(String password) {
		
		return password;
	}
	
	
}
