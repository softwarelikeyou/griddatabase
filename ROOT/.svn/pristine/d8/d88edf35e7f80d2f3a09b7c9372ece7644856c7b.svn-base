package com.griddatabase.viewcontroller.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.griddatabase.model.cache.CountyContourCache;
import com.softwarelikeyou.util.ZipUtil;

public class CountyOverlayKML extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CountyOverlayKML.class);
	
    public CountyOverlayKML() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
            response.getOutputStream().write(ZipUtil.zip(new ByteArrayInputStream(CountyContourCache.getCountyKML().toString().getBytes()), "countyOverlay.kmz"));
		    response.setContentType("application/vnd.google-earth.kmz kmz");
		    response.setHeader("Content-Disposition","inline; filename=countyOverlay.kmz;");
            response.getOutputStream().flush();
		}
		catch (IOException e) {
			logger.error(e);
			throw new IOException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
