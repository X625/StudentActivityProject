package edu.txstate.cs.service;

import edu.txstate.cs.model.bto.BookBTO;
import edu.txstate.cs.model.bto.PersonBTO;
import edu.txstate.cs.model.dto.Book;
import edu.txstate.cs.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class BookService {

    @Autowired
    BookRepo repo;

    public List<BookBTO> searchBooks(String Title, String Author, String ISBN)
    {

        return repo.findAll().stream()
                .filter(b -> StringUtils.isEmpty(Title) || b.getTitle().toLowerCase().contains(Title.toLowerCase()))
                .filter(b -> StringUtils.isEmpty(Author) || (b.getAuthors().stream().anyMatch(Author::equalsIgnoreCase)))
                .filter(b -> StringUtils.isEmpty(ISBN) || b.getIsbn().equalsIgnoreCase(ISBN))
                .map(b -> new BookBTO(b.getTitle(), b.getLibraryLocation() == null ? b.getBookStore() : b.getLibraryLocation()))
                .collect(toList());

    }
}
