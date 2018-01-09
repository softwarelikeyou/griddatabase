package com.softwarelikeyou;

import junit.framework.TestCase;

public class RegExTestCase extends TestCase {

	public void testRTLMPDate() {
		String name = "cdr.00013073.0000000000000000.20120902.203035.RTDLMPRNLZHUBNP6970_20120902_203001_xml.zip";
		
		String firstPass = name.replaceAll("^cdr\\.", "");
		System.out.println(firstPass);
		String secondPass = firstPass.replaceAll("^\\d+\\.", "");
		System.out.println(secondPass);
		String thirdPass = secondPass.replaceAll("^\\d+\\.", "");
		System.out.println(thirdPass);
		String fourthPass = thirdPass.substring(0, 8);
		System.out.println(fourthPass);
		

	}
}
