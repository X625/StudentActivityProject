package edu.txstate.cs.controller;

import edu.txstate.cs.model.bto.BookBTO;
import edu.txstate.cs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookService service;

    @PostMapping("/booksearch")
    public List<BookBTO> showBooks(@RequestBody Map<String, String> params)
    {
        String title = params.get("title");
        String author = params.get("author");
        String isbn = params.get("isbn");

        return service.searchBooks(title, author, isbn);
    }
}
