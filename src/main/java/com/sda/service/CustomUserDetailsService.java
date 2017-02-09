package com.sda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sda.enums.UserProfileType;
import com.sda.persistence.model.User;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		Optional<User> maybeUser = userService.findByLogin(login);
		if (!maybeUser.isPresent()) {
			throw new UsernameNotFoundException("Username not found");
		}
		User user = maybeUser.get();
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				true, true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(final User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (user.getUserType().equals(UserProfileType.USER)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		if (user.getUserType().equals(UserProfileType.ADMIN)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return authorities;
	}
}
