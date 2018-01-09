package com.softwarelikeyou.viewcontroller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.context.SecurityContextHolder;

import com.softwarelikeyou.exception.UserException;
import com.softwarelikeyou.facade.UserFacade;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.viewcontroller.WebConstants;

public class AccessControlFilter implements Filter {
	private static Logger logger = Logger.getLogger(AccessControlFilter.class);
	enum AccessControlledSection {

		USER("User");
	
		private String URI;

		private AccessControlledSection(String URI) {
			this.URI = URI;
		}

		public static AccessControlledSection fromURI(String URI) {

			if( URI != null ) {
				for( AccessControlledSection s : AccessControlledSection.values() ) {
					if( s.URI.equals(URI) ) return s;
				}
			}

			return null;

		}

	};

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		String URI = request.getRequestURI();

		String section = URI.indexOf("/", 1) > 0 ? URI.substring(1, URI.indexOf("/", 1)) : URI.substring(1);
		AccessControlledSection acs = AccessControlledSection.fromURI(section);

		if( acs != null ) {

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = null;

			if( principal instanceof User ) {
				user = (User) principal;
			}
			else if( principal != null ) {
				try {
				    user = UserFacade.findByUsername(principal.toString());
				}
				catch (UserException e) {
					logger.error("Could not retrieve user", e);
				}
			}

			if( user != null && !user.isAdministrator() && !userCanAccess(user, acs) ) {
				HttpServletResponse response = (HttpServletResponse) resp;
				response.sendRedirect(WebConstants.DEFAULT_SECURITY_REDIRECT);
				return;
			}

		}

		chain.doFilter(req, resp);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	protected boolean userCanAccess(User user, AccessControlledSection acs) {

		switch(acs) {
		case USER: return !user.isAdministrator();
		default: break;
		}

		return false;

	}

}
