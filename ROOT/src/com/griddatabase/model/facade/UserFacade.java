package com.griddatabase.model.facade;

import java.util.List;

import com.softwarelikeyou.GlobalConstants;
import com.softwarelikeyou.exception.UserException;
import com.griddatabase.model.dao.UserDAO;
import com.griddatabase.model.dao.UserDataDAO;
import com.griddatabase.model.entity.User;
import com.softwarelikeyou.model.entity.UserData;
import com.softwarelikeyou.I18NStrings;

public class UserFacade {	
	public static User findByUsername(final String username) throws UserException {
		User results = null;
		try {
		    results = new UserDAO().findByUsername(username);
		}
		catch (UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_RETRIEVE_USER, username);
		}
		return results;
	}
	
	public static List<User> findAll() throws UserException {
		List<User> results = null;;
		try {
		    results = new UserDAO().findAll();
		}
		catch (UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_RETRIEVE_ALL_USERS);
		}
		return results;
	}
	
	public static User createOrUpdate(User user) throws UserException {
		try {
			user = new UserDAO().createOrUpdate(user);
			EventFacade.fireUserCreated(user);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_PERSIST_USER, user.getUsername());
		}
		return user;
	}
	
	public static void remove(User user) throws UserException {
		try {
			new UserDAO().remove(user);
			EventFacade.fireUserDeleted(user);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_DELETE_USER, user.getUsername());
		}
	}
	
	public static User disable(User user) throws UserException {
		try {
			user.setEnabled(false);
			user = new UserDAO().createOrUpdate(user);
			EventFacade.fireUserUpdated(user);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_DISABLE_USER, user.getUsername());
		}
		return user;
	}
	
	public static User enable(User user) throws UserException {
		try {
			user.setEnabled(true);
			user = new UserDAO().createOrUpdate(user);
			EventFacade.fireUserUpdated(user);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_ENABLE_USER, user.getUsername());
		}
		return user;
	}
	
	public static User resetPassword(User user) throws UserException {
		try {
			user.setPassword(new PasswordEncoder().encodePassword(GlobalConstants.DEFAULT_PASSWORD, null));
			user = new UserDAO().createOrUpdate(user);
			EventFacade.fireUserUpdated(user);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_RESET_USER_PASSWORD, user.getUsername());
		}
		return user;
	}
	
	public static UserData findProperty(final User user, final String property) throws UserException {
		UserData results;
		try {
			results = new UserDataDAO().findProperty(user, property);
		}
		catch (UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_RETRIEVE_USERDATA, user.getUsername());
		}
		return results;
	}
	
	public static User changeType(User user) throws UserException {
		try {
			user = new UserDAO().createOrUpdate(user);
			EventFacade.fireUserUpdated(user);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_CHANGE_USER_TYPE, user.getUsername());
		}
		return user;
	}
	
	public static List<UserData> findProperties(final User user) throws UserException {
		List<UserData> results;
		try {
			results = new UserDataDAO().findAll(user);
		}
		catch (UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_RETRIEVE_USERDATA, user.getUsername());
		}
		return results;
	}
	
	public static UserData createOrUpdate(UserData userData) throws UserException {
		try {
			userData = new UserDataDAO().createOrUpdate(userData);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_PERSIST_USERDATA, userData.getValue());
		}
		return userData;
	}
	
	public static void remove(UserData userData) throws UserException {
		try {
			new UserDataDAO().remove(userData);
		}
		catch(UserException e) {
			throw new UserException(e, I18NStrings.FAILED_TO_DELETE_USERDATA, userData.getValue());
		}
	}
}