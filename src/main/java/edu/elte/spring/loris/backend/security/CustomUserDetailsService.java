package edu.elte.spring.loris.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Sets;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.service.UserService;

@Repository
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService uService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = uService.getUserbyUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("No such user: " + username);
		}

		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		org.springframework.security.core.userdetails.User currentUser = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), true, accountNonExpired, credentialsNonExpired,
				accountNonLocked, Sets.newHashSet(new SimpleGrantedAuthority("ROLE_USER")));

		return currentUser;
	}
}
