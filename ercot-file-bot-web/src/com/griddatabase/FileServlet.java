package com.griddatabase;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softwarelikeyou.util.FileUtil;
import com.softwarelikeyou.util.ResourceUtil;

public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String type = null;
	
	private String date = null;
	
    private PrintWriter writer = null;
    
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			response.setContentType("application/xml");
			writer = response.getWriter();
			writer.write("<?xml version=\"1.0\"?>" + "\n");
			type = request.getParameter("type");
			date = request.getParameter("date");
			if (type == null || type.length() == 0)
				writer.write("<error>" + "Type cannot be empty" + "</error>" + "\n");
			else {
			    writer.write("<" + type + ">" + "\n");
			    File dir = new File(FileUtil.getRootString() + FileUtil.getOSType().getSlash() + type + FileUtil.getOSType().getSlash() + date);
		        String[] chld = dir.list();
		        if (chld != null){
		            for(int i = 0; i < chld.length; i++) {
		        	    writer.write(" <file>" + "\n");
		        	    writer.write("  <name>" + chld[i] + "</name>" + "\n");
		        	    writer.write("  <href>" + ResourceUtil.get().getString("collector.base.file.url") + "/" + type + "/" + date + "/" + chld[i] + "</href>" + "\n");
		        	    writer.write(" </file>" + "\n");
		            }
		        }   
				writer.write("</" + type + ">" + "\n");
		    }
        } 
		catch (Exception e) {
			writer.write("<error>" + e.getMessage() + "</error>" + "\n");
		}
		finally {
		    writer.flush();
			writer.close();
		}
    }	
}
