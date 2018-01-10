package com.griddatabase.viewcontroller.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.griddatabase.model.facade.EventFacade;

public class SPPUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SPPUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long intervalDate = Long.valueOf(request.getParameter("intervalDate"));
		try {
			EventFacade.fireSPPUpdated(new Date(intervalDate));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
