package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.txstate.cs.model.dto.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
	Book findByTitle(String title);
}
