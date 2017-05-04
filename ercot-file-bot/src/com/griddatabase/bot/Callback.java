package com.griddatabase.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

public class Callback extends ParserCallback {
	private static String BASE_URL = "http://mis.ercot.com/";
	
	private Integer tableCount = 0;
	private Integer rowCount = 0;
	private String fileName;
	private String href;
	private List<Map<String, String>> filesToDownload = new ArrayList<Map<String, String>>();
	
	public List<Map<String, String>> getFilesToDownload() {
		return filesToDownload;
	}
	
	public void handleText(char[] data, int pos) {  
		if (tableCount == 3) {
	        String value = new String(data).trim();
	        if (rowCount == 1)
	            fileName = value;
		}
    }  
		  
	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos ){  
		if (t.toString().toLowerCase().equals(HTML.Tag.TABLE.toString().toLowerCase()))
			tableCount++;
		
		if (tableCount == 3) {
			if (t.toString().toLowerCase().equals(HTML.Tag.TR.toString().toLowerCase())) {
				rowCount = 0;
				fileName = "";
			}
			if (t.toString().toLowerCase().equals(HTML.Tag.TD.toString().toLowerCase()))
				rowCount++;
			if (rowCount == 4) {
				if (t.toString().toLowerCase().equals(HTML.Tag.A.toString().toLowerCase())) {
				    href = (String)a.getAttribute(HTML.Attribute.HREF);
				    if (fileName != null) {
				    	Map<String, String> result = new HashMap<String, String>();
	 			        result.put("name", fileName);
	 			        result.put("url", BASE_URL + href);
	 			        filesToDownload.add(result);
				    }
				}
			}
		}
    }
}
