package com.griddatabase.viewcontroller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.griddatabase.util.SessionUtil;

public class AutoRefreshFilter implements Filter {

	public void destroy() {

	}

	public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getRequestURI();

		if( uri.endsWith("/") )
			response.setHeader("Refresh", SessionUtil.getPageAutoRefresh().toString());

		chain.doFilter(req, resp);

	}

	public void init( FilterConfig config ) throws ServletException {

	}

}
