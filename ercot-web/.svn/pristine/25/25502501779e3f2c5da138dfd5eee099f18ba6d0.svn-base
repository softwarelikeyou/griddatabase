package com.softwarelikeyou.facade;

import org.apache.log4j.Logger;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.softwarelikeyou.exception.UserException;
import com.softwarelikeyou.model.entity.User;

public class UserDetailsService implements org.springframework.security.userdetails.UserDetailsService {
	private Logger logger = Logger.getLogger(UserDetailsService.class);
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user = null;
		try {
			user = UserFacade.findByUsername(username);
		} 
		catch (UserException e) {
			logger.error("Failed to load user from object model", e);
		}		
		if( user == null) 
			throw new UsernameNotFoundException("Username not found");
		return user;
	}
}
