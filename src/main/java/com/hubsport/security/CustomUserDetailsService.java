package com.hubsport.security;



import java.util.ArrayList;
import java.util.Collection;
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

import com.hubsport.domain.User;
import com.hubsport.service.UserService;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	
	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	UserService userService;
	
	// admin vs user, it shows status

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		logger.info("User : {}",user);
		if(user==null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getGrantedAuthorities(user));
	}
	
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         	int userStatus = user.getStatus();
            logger.info("UserProfile : {}", userStatus);
            if(userStatus == 0) {
            	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            } else if(userStatus == 1) {
            	authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } 
            
        logger.info("authorities : {}", authorities);
        return authorities;
    }


}
