package com.griddatabase.model.event;

import com.griddatabase.model.entity.User;

public class UserEvent extends Event {
	static final long serialVersionUID = 1L;

	public UserEvent(String name, User user) {
		super(name, user);
	}

	public User getUser() {
		return (User) getData();
	}	
}
