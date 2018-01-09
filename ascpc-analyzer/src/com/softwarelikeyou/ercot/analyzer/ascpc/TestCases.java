/*
package com.softwarelikeyou.ercot.analyzer.ascpc;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.pojo.ERCOTFile;

import junit.framework.TestCase;

public class TestCases extends TestCase {

	public void testXPath() {
		List<ERCOTFile> filesToDownload = new ArrayList<ERCOTFile>();
		ERCOTFile.FileType fileType = ERCOTFile.FileType.ASCPC;
		String date = "20121014";
		XPathFactory xFactor = XPathFactory.newInstance();
        XPath xPath = xFactor.newXPath();
        xPath.setNamespaceContext(new ERCOTNamespace());

		try {
			filesToDownload = WorkerHelper.getFilesToDownload(fileType.name().toLowerCase(), date);
			for (ERCOTFile file : filesToDownload) {
				if (!file.getName().endsWith("xml.zip"))
					continue;
				String content = WorkerHelper.getContents(file.getUrl());
				//System.out.println(content);
				InputSource inputSource = new InputSource(new StringReader(content));
				NodeList dams = (NodeList) xPath.evaluate("/ercot:DAMClearingPricesForCapacities/ercot:DAMClearingPricesForCapacity", inputSource, XPathConstants.NODESET);
	            for (int i = 0; i < dams.getLength(); i++) {
	                Node dam = dams.item(i);
	                String DeliveryDate = ((Node)xPath.evaluate("ercot:DeliveryDate", dam, XPathConstants.NODE)).getTextContent();
	                String HourEnding = ((Node)xPath.evaluate("ercot:HourEnding", dam, XPathConstants.NODE)).getTextContent();
	                String AncillaryType = ((Node)xPath.evaluate("ercot:AncillaryType", dam, XPathConstants.NODE)).getTextContent();
	                String MCPC = ((Node)xPath.evaluate("ercot:MCPC", dam, XPathConstants.NODE)).getTextContent();

	                System.out.println(DeliveryDate + "," + HourEnding + "," + AncillaryType + "," + MCPC);
	            }
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/
