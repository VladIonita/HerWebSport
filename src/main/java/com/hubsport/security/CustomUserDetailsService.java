package com.hubsport.security;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubsport.domain.Users;
import com.hubsport.service.UserService;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	
	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	UserService userService;
	
	// admin vs user, it shows status

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userService.findByUsername(username);
		logger.info("Username : {}",username);
		if(users==null) {
			logger.info("Usere de negasit");
			throw new UsernameNotFoundException("Username not found poate ca [plm");
		}
		return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(),true, true, true, true, getGrantedAuthorities(users));
	}
	
    private List<GrantedAuthority> getGrantedAuthorities(Users users){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            	authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        logger.info("authorities : {}", authorities);
        return authorities;
    }


}
