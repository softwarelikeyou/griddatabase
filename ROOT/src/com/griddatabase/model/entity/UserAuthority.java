package com.griddatabase.model.entity;

import org.springframework.security.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Override
	public String getAuthority() {
		return null;
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}


}
