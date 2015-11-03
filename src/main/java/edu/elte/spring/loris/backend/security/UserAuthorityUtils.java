package edu.elte.spring.loris.backend.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import edu.elte.spring.loris.backend.entity.User;

public final class UserAuthorityUtils {
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN",
            "ROLE_USER");
    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");

    public static Collection<? extends GrantedAuthority> createAuthorities(User u) {
    	
    	String username = u.getName();
        if (username.startsWith("admin")) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }

    private UserAuthorityUtils() {
    }
}
