package com.softwarelikeyou.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.softwarelikeyou.exception.FileException;

public class ERCOTFile {

	public enum FileType {
		UNKNOWN, 
		ASCPC,
		RTDAM,
		RTSPP,
		H48DAMHP,
		H48DAMAS,
		RTLMP; 
	}
	
	private String name;
	private String url;
	private Date date;
	private String content;
	
	public String getName() {
		return name;
	}
	
	public void setName(final String value) {
		name = value;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(final String value) {
		url = value;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(final Date value) {
		date = value;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(final String value) {
		content = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ERCOTFile other = (ERCOTFile) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} 
		else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	public static Date getTimeStamp(final FileType type, final String name) throws FileException {
		Date date = null;
		SimpleDateFormat format = null;
        String result = null;
		String result1 = null;
		String result2 = null;
		switch (type) {
		    case ASCPC:
		    	//cdr.00012329.0000000000000000.20121010.125408.DAMCPCNP4188_csv.zip
		    	result2 = name.replaceAll("_retry", "").substring(30, 38);
				format = new SimpleDateFormat("yyyyMMdd");
		    	break;
		    case RTSPP:
		    	//cdr.00012301.0000000000000000.20121010.000205.SPPHLZNP6905_20121010_0000_csv.zip
				format = new SimpleDateFormat("yyyyMMddkkmmss");
		        result = name.replaceAll("_retry", "").substring(59, 72);
				result1 = result.replaceAll("_", "");
				result2 = result1 + "00";
		    	break;
		    case RTLMP:
		    	//cdr.00013073.0000000000000000.20121010.000016.RTDLMPRNLZHUBNP6970_20121010_000001_csv.zip
		    	//cdr.00013073.0000000000000000.20121203.060047.RTDLMPRNLZHUBNP6970_retry_20121203_054501_csv.zip
				format = new SimpleDateFormat("yyyyMMddkkmmss");
		        result = name.replaceAll("_retry", "").substring(66, 79);
				result1 = result.replaceAll("_", "");
				result2 = result1 + "00";
				break;
			default:
				return new Date();
		}
		
		try {
			date = format.parse(result2);
		} 
		catch (ParseException e) {
			throw new FileException(e);
		}
		return date;
	}
}