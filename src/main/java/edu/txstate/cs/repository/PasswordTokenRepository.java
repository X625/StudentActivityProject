package edu.txstate.cs.repository;

import org.springframework.data.repository.CrudRepository;

import edu.txstate.cs.model.dto.PasswordResetToken;

public interface PasswordTokenRepository extends CrudRepository<PasswordResetToken, Long>{

	PasswordResetToken findByToken(String token);

}
