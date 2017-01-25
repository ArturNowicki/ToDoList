package com.sda.persistence.dao;

import java.util.Optional;

import com.sda.persistence.model.PasswordResetToken;

public interface PasswordResetTokenDao {

	Optional<PasswordResetToken> findByToken(String token);
	
	void save(PasswordResetToken token);

	void deleteById(int id);
}
