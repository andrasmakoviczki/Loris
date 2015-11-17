package edu.elte.spring.loris.backend.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Sets;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.service.UserService;
import edu.elte.spring.loris.backend.service.UserServiceImpl;
import edu.elte.spring.loris.backend.util.UserException;

public class CustomUserDetailsService implements UserDetailsService{
	
	UserService uService;
	
	public CustomUserDetailsService() {
		this.uService = new UserServiceImpl();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = uService.findUserbyUsername(username);
		
		if(user == null){
			throw new UsernameNotFoundException("No such user: " + username);
	    }
		
		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	
	    org.springframework.security.core.userdetails.User currentUser = new org.springframework.security.core.userdetails.User(
	    		user.getUsername(),
	            user.getPassword().toLowerCase(),
	            true,
	            accountNonExpired,
	            credentialsNonExpired,
	            accountNonLocked,
	            Sets.newHashSet(new SimpleGrantedAuthority("ROLE_USER")));
	    
	    return currentUser;
	}
}
