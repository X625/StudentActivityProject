package edu.txstate.cs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.txstate.cs.model.dto.PasswordResetToken;
import edu.txstate.cs.model.dto.User;

public interface PasswordTokenRepository extends CrudRepository<PasswordResetToken, Long>{

	PasswordResetToken findByToken(String token);
	
	PasswordResetToken findByUserAndUpdatedIsNull(User user);
	
	List<PasswordResetToken> findByUser(User user);

}
