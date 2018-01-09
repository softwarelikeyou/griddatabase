package com.softwarelikeyou.viewcontroller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.security.context.SecurityContextHolder;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.softwarelikeyou.exception.UserException;
import com.softwarelikeyou.facade.UserFacade;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.model.entity.User.UserType;
import com.softwarelikeyou.model.entity.UserData;
import com.softwarelikeyou.util.ResourceUtil;

public class SessionUtil {
	private static Logger logger = Logger.getLogger(SessionUtil.class);
	private static final String SESSION_USER_PROPERTY = "sessionUser";
	
	public static Boolean isLoggedIn() {
		return getCurrentUser() != null;
	}
	
	public static User getCurrentUser() {
		Session session = Sessions.getCurrent();
		User user = null;
		
		if( session != null )
			user = (User) session.getAttribute(SESSION_USER_PROPERTY);

		if( user == null ) {

			if( SecurityContextHolder.getContext().getAuthentication() == null) 
				return null;
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if( principal instanceof User )
				user = (User) principal;
			else if( principal != null ) {
				try {
					    user = UserFacade.findByUsername(principal.toString());
					
				}
				catch (Exception e) {
					logger.error(e);
				}
				
			}
			session.setAttribute(SESSION_USER_PROPERTY, user);
		}

		return user;
	}

	public static String getCurrentUsername() {
		User user = getCurrentUser();
		return user == null ? "" : user.getUsername();
	}

	public static String getCurrentDisplayName() {
		User user = getCurrentUser();
		return user == null ? "" : user.getDisplay();
	}
	
	public static Boolean isAdministrator() {
		if (!isLoggedIn())
			return false;
		return UserType.ADMINISTRATOR.equals(getCurrentUser().getType());
	}

	public static Boolean isPremium() {
		if (!isLoggedIn())
			return false;
		return UserType.PREMIUM.equals(getCurrentUser().getType()) || isAdministrator();
	}
	
	public static Boolean isFreemium() {
		if (!isLoggedIn())
			return true;
		return UserType.FREEMIUM.equals(getCurrentUser().getType()) || UserType.PREMIUM.equals(getCurrentUser().getType()) || isAdministrator();
	}
	
	public static Locale getSelectedLocale() { 
		return Locale.getDefault();
	}
	
	public static void setCurrentUser(final User user) {
		Session session = Sessions.getCurrent();
		session.setAttribute(SESSION_USER_PROPERTY, user);
	}
	
	public static UserData getUserData(final String property) {
		UserData result = null;
		try {
			if (SessionUtil.isLoggedIn())
		        result = UserFacade.findProperty(getCurrentUser(), property);
		}
		catch (UserException e) {
			logger.error(e);
		}
		return result;
	}
	
	public static UserData setUserData(final UserData userData) {
		UserData result = null;
		try {
			if (userData.getValue() != null && userData.getValue().length() > 0)
		       result = UserFacade.createOrUpdate(userData);
			else
				UserFacade.remove(userData);
		}
		catch (UserException e) {
			logger.error(e);
		}
		return result;
	}
	
	public static String getPageAutoRefresh() {
		return ResourceUtil.get().getString("default-page-auto-refresh");
	}
	
	private static int screenWidth = 0;
	public static void setScreenWidth(final int width) {
		screenWidth = width;
	}
	public static int getScreenWidth() {
		return screenWidth;
	}
	private static int screenHeight = 0;
	public static void setScreenHeight(final int height) {
		screenHeight = height;
	}
	public static int getScreenHeight() {
		return screenHeight;
	}
}
