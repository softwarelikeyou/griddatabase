package com.griddatabase.viewcontroller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.griddatabase.model.cache.RTLMPCache;
import com.griddatabase.model.cache.RTSPPCache;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;

public class SPPMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(SPPMap.class);

    public SPPMap() {
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
		    buffer.append("  <meta http-equiv=\"refresh\" content=\"300\">");
		    buffer.append("  <style>" + "\n");
		    buffer.append("   html, body, #map_canvas { margin: 0; padding: 0; height: 100%; }" + "\n");
		    buffer.append(" </style>" + "\n");
		    buffer.append("  <script src=\"https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=weather\"></script>" + "\n");
		    buffer.append("  <script>" + "\n");
		    buffer.append("   var map;" + "\n");
		    buffer.append("   var tooltip;" + "\n");
		    buffer.append("   var icon;" + "\n");
		    buffer.append("   var latlng;" + "\n");
		    buffer.append("   var options;" + "\n");
		    buffer.append("   var infowindow = new google.maps.InfoWindow();" + "\n");
		    buffer.append("   function initialize() {" + "\n");
		    buffer.append("       var center = new google.maps.LatLng(29.416667,-98.5);" + "\n");
		    buffer.append("       var mapOptions = { zoom: 6, center: center, mapTypeId: google.maps.MapTypeId.ROADMAP }" + "\n");
		    buffer.append("       map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);" + "\n");
		    buffer.append("\n");
		    try {
			    for (RTSPP rtspp : RTSPPCache.getListByType("RN")) {
			    	SettlementPoint settlementPoint = SettlementPointFacade.findById(rtspp.getSettlementPointName());
			        if (settlementPoint == null) continue;  
			        
				    StringBuffer tooltip = new StringBuffer();
				    tooltip.append(rtspp.getSettlementPointName() + " / ");
				    tooltip.append(rtspp.getSettlementPointType() + " / ");
				    tooltip.append("$" + rtspp.getSettlementPointPrice() + " / ");
				    tooltip.append(settlementPoint.getTemperature() + "F\u00B0");
				    
				    StringBuffer popup = new StringBuffer();
				    popup.append("<b>SettlementPoint</b>:       " + rtspp.getSettlementPointName() + "<br>");
				    popup.append("<b>Type</b>:                  " + rtspp.getSettlementPointType() + "<br>");
				    popup.append("<b>Price</b>:                $" + rtspp.getSettlementPointPrice() + "<br>");
				    popup.append("<b>Temperture</b>:            " + settlementPoint.getTemperature() + "F\u00B0" + "<br>");
				    
				    RTLMP rtlmp = RTLMPCache.getLatest(rtspp.getSettlementPointName());
				    if (rtlmp != null)
				    	popup.append("<b>Latest Forecast:</b>  $" + rtlmp.getLMP() + "<br>");
				    
			    	buffer.append("    tooltip = '" + tooltip.toString() + "';");
			    	buffer.append("    icon = '/images/factory-16x16.png';");
				    buffer.append("    latLng = new google.maps.LatLng(" + Double.valueOf(settlementPoint.getLatitude()) + "," + Double.valueOf(settlementPoint.getLongitude()) + ");" + "\n");
				    buffer.append("    options = { position: latLng, map: map, title: tooltip, icon: icon }" + "\n");
				    buffer.append("    var " + rtspp.getSettlementPointName() + "_marker = new google.maps.Marker(options);" + "\n");
				    buffer.append("    google.maps.event.addListener(" + rtspp.getSettlementPointName() + "_marker, 'click', (function(" + rtspp.getSettlementPointName() + "_marker) { return function() { infowindow.setContent(\"" + popup.toString() + "\"); infowindow.open(map, " + rtspp.getSettlementPointName() + "_marker); }})(" + rtspp.getSettlementPointName() + "_marker));" + "\n"); 
				    buffer.append("\n");
			    }
			}
			catch (Exception e) {
				logger.equals(e);
			}
		    buffer.append("       var weatherLayer = new google.maps.weather.WeatherLayer({temperatureUnits: google.maps.weather.TemperatureUnit.FAHRENHEIT});" + "\n");
            buffer.append("       weatherLayer.setMap(map);" + "\n");
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
