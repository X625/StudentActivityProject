package edu.txstate.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.txstate.cs.model.bto.BookBTO;
import edu.txstate.cs.service.BookService;


@Controller
public class BookController {
	
	@Autowired
	private BookService svc;
	
	private void setGetMappingModel(Model model) {
		List<BookBTO> books = svc.findAllUserBooks();
		double total = books == null ? 0 : books.stream().mapToDouble(BookBTO::getPrice).sum();
		model.addAttribute("userbooks",books);
		model.addAttribute("total", total);
		model.addAttribute("book", new BookBTO());
	}
	
	@GetMapping("/books")
	public String showTextbookPage(Model model) {
		setGetMappingModel(model);
		return "books";
	}
	
	@PostMapping("/books")
	public String searchBook(@ModelAttribute("book") BookBTO book, Model model) {
		String author = book.getAuthor();
		String isbn = book.getIsbn();
		String title = book.getTitle();
		setGetMappingModel(model);
		List<BookBTO> result = svc.searchBooks(author, title, isbn);
		model.addAttribute("books", result);
		return "books";
	}
	
	@PostMapping("/purchase/books/{id}")
	public String purchaseBook(@PathVariable("id") long id, Model model) {
		svc.purchaseBook(id);
		return "redirect:/books";
	}

}
