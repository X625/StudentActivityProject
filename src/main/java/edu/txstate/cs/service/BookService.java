package edu.txstate.cs.service;

import static java.util.stream.Collectors.toList;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.txstate.cs.helper.Util;
import edu.txstate.cs.model.bto.BookBTO;
import edu.txstate.cs.model.dto.Book;
import edu.txstate.cs.model.dto.User;
import edu.txstate.cs.repository.BookRepo;
import edu.txstate.cs.repository.UserRepo;


@Service
public class BookService {
	
	@Autowired
	private UserRepo prepo;
	
	@Autowired
	private BookRepo brepo;
	
	
	public List<BookBTO> findAllUserBooks(){
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User p = prepo.findByUsername(username);
		return p.getBooks().stream().map(Util::mapToBookBTO).collect(toList());
	}
	
	
	public List<BookBTO> searchBooks(String author, String title, String isbn){
		
		final List<BookBTO> usersBook = findAllUserBooks();
		return brepo.findAll().stream()
					.filter(b -> StringUtils.isEmpty(isbn) || b.getIsbn().equalsIgnoreCase(isbn))
					.filter(b -> StringUtils.isEmpty(author) || b.getAuthor().contains(author))
					.filter(b -> StringUtils.isEmpty(title) || b.getTitle().toLowerCase().contains(title.toLowerCase()))
					.map(Util::mapToBookBTO)
					.map(bto -> { 
						if(usersBook.contains(bto)) {
							bto.setPurchased(true);
						}
						return bto;
					})
					.collect(toList());
	}



	public boolean purchaseBook(long id) {
		try {
			Book b = brepo.findById(id).get();
			User p = prepo.findByUsername("aidinx625");
			double total = p.getBooks().stream().mapToDouble(bk -> bk.getPrice()).sum();
			double purchasePrice = b.getPrice();
			if(total > 200) {
				purchasePrice *= .9;
			}
			p.addPurchasedBook(b, purchasePrice);
			prepo.save(p);
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}

}
