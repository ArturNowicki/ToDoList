package com.sda.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sda.persistence.dao.PasswordResetTokenDao;
import com.sda.persistence.model.PasswordResetToken;
import com.sda.persistence.model.User;

@Service("userSecurityService")
@Transactional
public class UserSecurityServiceImpl implements UserSecurityService {

	@Autowired
	PasswordResetTokenDao passwordResetTokenDao;

	@Override
	public String validatePasswordResetToken(int id, String token) {
		Optional<PasswordResetToken> maybePassToken = passwordResetTokenDao.findByToken(token);
		if (!maybePassToken.isPresent() || maybePassToken.get().getUser().getId() != id) {
			return "Invalid token";
		}
		final Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		if(maybePassToken.get().getExpiryDate().before(currentDate)) {
			return "Expired token";
		}
		final User user = maybePassToken.get().getUser();
		final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		System.out.println("AUTHENTICATION: " + auth);
		return null;
	}

}
