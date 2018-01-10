package com.griddatabase.viewcontroller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.griddatabase.model.cache.RTSPPCache;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;

public class SPPHeatMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(SPPHeatMap.class);

    public SPPHeatMap() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();

		    StringBuffer buffer = new StringBuffer();
		    buffer.append("<html>" + "\n");
		    buffer.append(" <head>" + "\n");
		    buffer.append("  <meta http-equiv=\"refresh\" content=\"60\">");
		    buffer.append("  <style>" + "\n");
		    buffer.append("   html, body, #map_canvas { margin: 0; padding: 0; height: 100%; }" + "\n");
		    buffer.append(" </style>" + "\n");
		    buffer.append("  <script src=\"https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=visualization\"></script>" + "\n");
		    buffer.append("  <script>" + "\n");
		    buffer.append("   var map;" + "\n");
		    buffer.append("   function initialize() {" + "\n");
		    buffer.append("       var center = new google.maps.LatLng(29.416667,-98.5);" + "\n");
		    buffer.append("       var mapOptions = { zoom: 6, center: center, mapTypeId: google.maps.MapTypeId.ROADMAP }" + "\n");
		    buffer.append("       map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);" + "\n");
		    buffer.append("\n");
	        buffer.append("       var taxiData = [" + "\n");
		    StringBuffer latlngs = new StringBuffer();
		    try {
			    for (RTSPP rtspp : RTSPPCache.getListByType("RN")) {
			    	SettlementPoint settlementPoint = SettlementPointFacade.findById(rtspp.getSettlementPointName());
			        if (settlementPoint == null) continue;  
			        latlngs.append("               new google.maps.LatLng(" + Double.valueOf(settlementPoint.getLatitude()) + "," + Double.valueOf(settlementPoint.getLongitude()) + ")," + "\n");
			    }
			}
			catch (Exception e) {
				logger.equals(e);
			}
		    if (latlngs.toString().length() > 0)
		        buffer.append(latlngs.toString().substring(0, latlngs.toString().lastIndexOf(",")));
            buffer.append("                       ];" + "\n");
		    buffer.append("\n");
	        buffer.append("       var pointArray = new google.maps.MVCArray(taxiData);" + "\n");
	        buffer.append("       var heatmap = new google.maps.visualization.HeatmapLayer({data: pointArray});" + "\n");
	        buffer.append("       heatmap.setMap(map);" + "\n");
		    buffer.append("   }" + "\n");
		    buffer.append("   google.maps.event.addDomListener(window, 'load', initialize);" + "\n");
		    buffer.append("  </script>" + "\n");
		    buffer.append(" </head>" + "\n");
		    buffer.append(" <body>" + "\n");
		    buffer.append("  <div id=\"map_canvas\"></div>" + "\n");
		    buffer.append(" </body>" + "\n");
		    buffer.append("</html>" + "\n");
		    out.println(buffer.toString());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
