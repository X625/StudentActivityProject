package edu.txstate.cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.txstate.cs.model.Person;
import edu.txstate.cs.repository.PersonRepo;

@SpringBootApplication
public class StudentActivityProjectApplication implements CommandLineRunner{

	@Autowired
	private PersonRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentActivityProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repo.save(new Person("John","Smith"));
	}
	
}
