package com.griddatabase.collector.worker;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.griddatabase.collector.kml.KMLOpenGISNamespaceContext;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.griddatabase.scheduler.Jobs;

public class SettlementPointWorker implements StatefulJob {

	public static Logger logger = Logger.getLogger(SettlementPointWorker.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
            XPathFactory xFactor = XPathFactory.newInstance();
	        XPath xPath = xFactor.newXPath();
            xPath.setNamespaceContext(new KMLOpenGISNamespaceContext());
        
            URL url = new URL("http://www.ercot.com/content/cdr/contours/rtmLmpHg.kml"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/kml"); 
            if (connection.getResponseCode() != 200) {
    	        logger.error("HTTP error code : "+ connection.getResponseCode());
    	        return;
            }
         
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
    	     			
            StringBuffer response = new StringBuffer();
    	    String string;
    	    while ((string = br.readLine()) != null)
    	        response.append(string);
    	 
            connection.disconnect();
    		
		    InputSource inputSource = new InputSource(new StringReader(response.toString()));  
			
            NodeList places = (NodeList) xPath.evaluate("/kml:kml/kml:Document/kml:Folder/kml:Placemark", inputSource, XPathConstants.NODESET);
            
            for (int i = 0; i < places.getLength(); i++) {
                Node place = places.item(i);
                String folderName = ((Node)xPath.evaluate("kml:name", place.getParentNode(), XPathConstants.NODE)).getTextContent();
                if (!folderName.equals("LMP Points"))
            	    continue;
                String name = ((Node)xPath.evaluate("kml:name", place, XPathConstants.NODE)).getTextContent();
                String description = ((Node)xPath.evaluate("kml:description", place, XPathConstants.NODE)).getTextContent();
                String[] coords = ((Node)xPath.evaluate("kml:Point/kml:coordinates", place, XPathConstants.NODE)).getTextContent().split(",");
            
                SettlementPoint settlementPoint = SettlementPointFacade.findById(name);
                if (settlementPoint == null) {
                	settlementPoint = new SettlementPoint();
                	settlementPoint.setName(name);
                	settlementPoint.setLatitude(coords[1]);
                	settlementPoint.setLongitude(coords[0]);
                	settlementPoint.setDescription(description);
                	SettlementPointFacade.createOrUpdate(settlementPoint);
    				Jobs.sechduleStation(settlementPoint);
                	logger.info("Saving settlementPoint " + name);
                }
            }
		}
		catch (Exception e) {
			logger.error(e.getCause(), e);
		}
	}
	
	/*
    public void descr(final String value) {
        try {
    	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	    Document d = factory.newDocumentBuilder().parse(new InputSource(new StringReader(value)));

    	    NodeList list = d.getChildNodes();
    	    
    	    for (int i = 0; i < list.getLength(); i++) {
    	    	Node node = (Node) list.item(i);
    	    	//System.out.println(node.getNodeName() + "-" + node.getNodeValue());
    	    }


    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}

    }
	 */
}
