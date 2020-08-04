package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import edu.txstate.cs.model.dto.User;

public interface UserRepo extends JpaRepository<User, Long>,  JpaSpecificationExecutor<User>{
	User findByUsername(String username);

	User findByEmail(String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	User findByUsernameOrEmail(String username, String email);

}
