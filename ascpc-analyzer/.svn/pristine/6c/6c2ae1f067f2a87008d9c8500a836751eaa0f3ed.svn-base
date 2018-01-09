/*
 package com.softwarelikeyou.ercot.analyzer.ascpc;


import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.softwarelikeyou.analyzer.Saver;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;

public class XMLSaver extends Saver {

	private static Logger logger = Logger.getLogger(XMLSaver.class);

	private DailyWorker daily = new DailyWorker();
	
	@Override
	public boolean execute() {
		if (file == null)
			return false;
		try {
			XPathFactory xFactor = XPathFactory.newInstance();
	        XPath xPath = xFactor.newXPath();
	        xPath.setNamespaceContext(new ERCOTNamespace());
	        InputSource inputSource = new InputSource(new StringReader(file.getContent()));
			NodeList dams = (NodeList) xPath.evaluate("/ercot:DAMClearingPricesForCapacities/ercot:DAMClearingPricesForCapacity", inputSource, XPathConstants.NODESET);
            for (int i = 0; i < dams.getLength(); i++) {
                Node dam = dams.item(i);
                String DeliveryDate = ((Node)xPath.evaluate("ercot:DeliveryDate", dam, XPathConstants.NODE)).getTextContent();
                String HourEnding = ((Node)xPath.evaluate("ercot:HourEnding", dam, XPathConstants.NODE)).getTextContent();
                String AncillaryType = ((Node)xPath.evaluate("ercot:AncillaryType", dam, XPathConstants.NODE)).getTextContent();
                String MCPC = ((Node)xPath.evaluate("ercot:MCPC", dam, XPathConstants.NODE)).getTextContent();
                
                ASCPC ascpc = new ASCPC();
            	ascpc.setIntervalDate(getIntervalDateTime(DeliveryDate + " " + HourEnding));
    		    switch (AncillaryType) {
    		        case "NSPIN":
    		        	ascpc.setNSPIN(toFloat(MCPC));
    		        	break;
    		        case "REGDN":
    		        	ascpc.setREGDN(toFloat(MCPC));
    		        	break;
    		        case "REGUP":
    		        	ascpc.setREGUP(toFloat(MCPC));
    		        	break;
    		        case "RRS":
    		        	ascpc.setRRS(toFloat(MCPC));
    		        	break;
    		    }
				ASCPCFacade.createOrUpdate(ascpc);
				if (i == 0)
					daily.setIntervalDate(ascpc.getIntervalDate());
            }
			daily.execute();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		    logger.error(e.getCause(),e);
		    return false;
		}
	}
}
*/