package com.softwarelikeyou.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.facade.RTSPPDailyFacade;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH15M;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH1H;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH24H;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH30M;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH5M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU15M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU1H;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU24H;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU30M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU5M;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN15M;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN1H;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN24H;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN30M;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN5M;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ15M;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ1H;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ24H;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ30M;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ5M;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC15M;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC1H;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC24H;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC30M;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC5M;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN15M;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN1H;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN24H;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN30M;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN5M;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN15M;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN1H;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN24H;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN30M;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN5M;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN15M;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN1H;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN24H;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN30M;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN5M;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH15M;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH1H;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH24H;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH30M;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH5M;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH15M;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH1H;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH24H;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH30M;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU15M;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU1H;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU24H;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU30M;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN1H;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN24H;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN30M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ15M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ1H;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ24H;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ30M;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC15M;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC1H;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC24H;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC30M;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW15M;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW1H;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW24H;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW30M;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW15M;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW1H;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW24H;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW30M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN1H;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN24H;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN30M;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN15M;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN1H;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN24H;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN30M;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN15M;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN1H;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN24H;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN30M;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH15M;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH1H;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH24H;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH30M;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.Util;
import com.softwarelikeyou.util.ZipUtil;

import junit.framework.TestCase;

    public class ERCOTWebTestCases extends TestCase {
 
    private Properties hibernateProperties = new Properties();
 
    //private Configuration hibernateConfiguration = new Configuration();
 
    public void setUp() throws Exception {
        hibernateProperties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        hibernateProperties.setProperty(Environment.URL, "jdbc:mysql://192.168.0.201/ercot");
        hibernateProperties.setProperty(Environment.USER, "root");
        hibernateProperties.setProperty(Environment.PASS, "dinky01");
        hibernateProperties.setProperty(Environment.SHOW_SQL, "true");
        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
        hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        
       // hibernateConfiguration
       // .addAnnotatedClass(RTSPPHU15M.class)
       // .setProperties(hibernateProperties);
    }
 
    public void tearDown() throws Exception {
       // HibernateUtil.shutdown();
    }
    
	public void testHibernate() {
    	//HibernateUtil.getInstance(hibernateConfiguration);
    	Session session = null;
    	try {    		
    		session = HibernateUtil.getSessionFactory().openSession();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		if (session.isConnected())
    			session.close();
    	}    	
    }
	
	public void testASCPC() {
		try {
	   // HibernateUtil.getInstance(hibernateConfiguration);
	    Date start = Util.stringToDate("11/06/2012 00:00:00", "MM/dd/yyyy kk:mm:ss");
	    Date end = Util.stringToDate("11/07/2012 00:00:00", "MM/dd/yyyy kk:mm:ss");

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);

		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
    	
		endCal.set(Calendar.HOUR_OF_DAY, 23);
		endCal.set(Calendar.MINUTE, 59);
		endCal.set(Calendar.SECOND, 59);
		
		List<ASCPC> ascpcs = ASCPCFacade.findBetweenDates(startCal.getTime(), endCal.getTime());
		
		for (ASCPC ascpc : ascpcs) {
			System.out.println(ascpc.getIntervalDate());
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testRTSPPDaily() {
		try {
	    	//HibernateUtil.getInstance(hibernateConfiguration);
	    	Date intervalDate = Util.stringToDate("20121102", "yyyyMMdd");
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(intervalDate);
    		calendar.set(Calendar.HOUR_OF_DAY, 0);
    		calendar.set(Calendar.MINUTE, 0);
    		calendar.set(Calendar.SECOND, 0);
    		
    		Date current = calendar.getTime();
    		calendar.add(Calendar.DATE, -1);
    		Date previous = calendar.getTime();
    		
    		String name = "HB_HOUSTON";
    		
    		Float avg = RTSPPFacade.findOffPeakAverage(RTSPPHU15M.class, current, name);
			Float low = RTSPPFacade.findOffPeakMin(RTSPPHU15M.class, current, name);
			Float high = RTSPPFacade.findOffPeakMax(RTSPPHU15M.class, current, name);
			Float avgChg = avg - RTSPPFacade.findOffPeakAverage(RTSPPHU15M.class, previous, name);
			 
			System.out.println("Avg    = " + new DecimalFormat("##.##").format(avg));
			System.out.println("Low    = " + low);
			System.out.println("High   = " + high);
			System.out.println("AvgChg = " + avgChg);
			
			System.out.println("---------------");
			
			for (Daily daily : RTSPPDailyFacade.findByBetweenDates(current, current)) {
				if (daily.getName().startsWith("HB"))
					System.out.println(daily.getIntervalDate() + " - " + daily.getLow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testRTSPP() {
		try {
		   // HibernateUtil.getInstance(hibernateConfiguration);

			Calendar start = Calendar.getInstance();
			start.setTime(new Date());
			start.set(Calendar.HOUR_OF_DAY, 0);
			start.set(Calendar.MINUTE, 0);
			start.set(Calendar.SECOND, 0);
			
			Calendar end = Calendar.getInstance();
			end.setTime(new Date());
			end.set(Calendar.HOUR_OF_DAY, 23);
			end.set(Calendar.MINUTE, 59);
			end.set(Calendar.SECOND, 59);
			
			List<RTSPP> list = RTSPPFacade.findByIntervalDatesSettlementPoint(RTSPPHU15M.class, start.getTime(), end.getTime(), "HB_HOUSTON");
			for (RTSPP rtspp : list)
				System.out.println(rtspp.getIntervalDate() + " - " + rtspp.getSettlementPointPrice());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testRTLMP() {
		try {
	    	//HibernateUtil.getInstance(hibernateConfiguration);
			Calendar start = Calendar.getInstance();
			start.setTime(new Date());
			start.add(Calendar.DATE, -5);
			start.set(Calendar.HOUR_OF_DAY, 0);
			start.set(Calendar.MINUTE, 0);
			start.set(Calendar.SECOND, 0);
			
			Calendar end = Calendar.getInstance();
			end.setTime(new Date());
			end.add(Calendar.DATE, -5);
			end.set(Calendar.HOUR_OF_DAY, 23);
			end.set(Calendar.MINUTE, 59);
			end.set(Calendar.SECOND, 59);
    					
			Map<Date, Map<Integer, Float>> map = new HashMap<Date, Map<Integer, Float>>();

		    for (RTLMP rtlmp : RTLMPFacade.findByBetweenIntervalEndingDatesSettlementPoint(RTLMPHU5M.class, start.getTime(), end.getTime(), "HB_HOUSTON")) {
				if (map.containsKey(rtlmp.getIntervalEnding())) 
					map.get(rtlmp.getIntervalEnding()).put(rtlmp.getIntervalId(), rtlmp.getLMP());
				else {
				    Map<Integer, Float> values = new HashMap<Integer, Float>();
					values.put(rtlmp.getIntervalId(), rtlmp.getLMP());
					map.put(rtlmp.getIntervalEnding(), values);
				}
			}
		    
		    SortedSet<Date> keys = new TreeSet<Date>(map.keySet());
		    int i = 1;
		    for (Date key : keys) {
		    	float open = map.get(key).get(getOpen(map.get(key).keySet()));
		    	float close = map.get(key).get(getClose(map.get(key).keySet()));
		    	float low = getLow(map.get(key));
		    	float high = getHigh(map.get(key));
		    	System.out.println(i++ + "  " + key + " - " + open + ", " + high + ", " + close + ", " + low + ", " + map.get(key).size());
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Integer getOpen(Set<Integer> set) {
		Integer value = 9999;
		for (Integer i : set) {
			if (i < value)
			    value = i;
		}
		return value;
	}
	
	private Integer getClose(Set<Integer> set) {
		Integer value = -9999;
		for (Integer i : set) {
			if (i > value)
			    value = i;
		}
		return value;
	}
	
	private float getLow(Map<Integer, Float> map) {
		float value = 9999.9f;
		for (Integer id : map.keySet()) {
    		if (map.get(id) < value)
    			value = map.get(id);
		}
		return value;
	}
	
	private float getHigh(Map<Integer, Float> map) {
		float value = -9999.9f;
		for (Integer id : map.keySet()) {
    		if (map.get(id) > value)
    			value = map.get(id);
		}
		return value;
	}
	
	public void testRestful() {
		try {
			StringBuffer request = new StringBuffer("http://192.168.0.202:8080/restful/RTLMP/findByBetweenIntervalEndingDatesSettlementPoint");
			request.append("?settlementPoint=HB_HOUSTON");
			request.append("&settlementPointType=HU");
			request.append("&timeInterval=15M");
			request.append(URLEncoder.encode("&start=20121027 11:00:00", "UTF-8"));
			request.append(URLEncoder.encode("&end=20121027 11:00:00", "UTF-8"));
			System.out.println(request.toString());
			URL url = new URL(request.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "UTF-8");
	        conn.setDoOutput(true);
	        conn.setReadTimeout(10000);
	        conn.connect();
	        
			if (conn.getResponseCode() != 200)
				throw new IOException(conn.getResponseCode() + " - " + conn.getResponseMessage());

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) 
				sb.append(line);
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testCRR() {
		try {
		  //  HibernateUtil.getInstance(hibernateConfiguration);

			Calendar start = Calendar.getInstance();
			start.setTime(new Date());
			start.set(Calendar.HOUR_OF_DAY, 0);
			start.set(Calendar.MINUTE, 0);
			start.set(Calendar.SECOND, 0);
			
			Calendar end = Calendar.getInstance();
			end.setTime(new Date());
			end.set(Calendar.HOUR_OF_DAY, 23);
			end.set(Calendar.MINUTE, 59);
			end.set(Calendar.SECOND, 59);
			
			Map<Date, Double> map = RTSPPFacade.findPriceChangeBetweenSettlementPoints(RTSPPHU15M.class, start.getTime(), end.getTime(), "HB_HOUSTON", "HB_NORTH");
			for (Date key : map.keySet()) {
				System.out.println(key + " - " + map.get(key));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testChartDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		TreeMap<Date, Integer> map = new TreeMap<Date, Integer>();
	    DateFormat format = new SimpleDateFormat("kk:mm:ss");
	    Integer i = 0;
		for (int hour = 0; hour <= 23; hour++) {
			for (int minute = 0; minute <= 55; minute+=5) {
				try {
			        map.put(format.parse(String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":00"), i++);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		for (Date key : map.keySet()) {
			System.out.println(map.get(key) + "  " + format.format(key));
		}
	}
	
	private Integer getMinutes(final Integer minute) {
		switch (minute) {
		    case 1:
			    return 15;
		    case 2:
			    return 30;
		    case 3:
			    return 45;
		    case 4:
			    return 0;
			default:
				return 59;
		}
	}
	
	public void testERCOTFile() {
		try {
			URL url = new URL("http://192.168.0.201/ercot-file-collector/listing/rtspp/20121122/cdr.00012301.0000000000000000.20121122.000205.SPPHLZNP6905_20121122_0000_csv.zip");
			//url = new URL("http://192.168.0.201/ercot-file-collector/listing/rtspp/20121122/cdr.00012301.0000000000000000.20121122.001706.SPPHLZNP6905_20121122_0015_csv.zip");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			String file = new String(ZipUtil.unzip(is));
			System.out.println(file);
			
			
        	CSVReader reader = new CSVReader(new StringReader(file), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
            CsvToBean<CSVPOJO> bean = new CsvToBean<CSVPOJO>();
        	ColumnPositionMappingStrategy<CSVPOJO> strat = new ColumnPositionMappingStrategy<CSVPOJO>();
            strat.setType(CSVPOJO.class);
            strat.setColumnMapping(CSVPOJO.columns);
			List<CSVPOJO> rows = bean.parse(strat, reader);
			for (CSVPOJO row : rows) {
				if (!row.getSettlementPointName().equals("HB_HOUSTON"))
					continue;
				
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(Util.stringToDate(row.getDeliveryDate() + " 24:00:00", "MM/dd/yyyy kk:mm:ss"));
		    	Integer hour = Integer.valueOf(row.getDeliveryHour());
		    	Integer interval = Integer.valueOf(row.getDeliveryInterval());
		    	Integer second = 0;
		    	
		    	if (hour == 24 && interval == 4) {
		    		calendar.add(Calendar.DATE, 1);
		    		hour = 0;
		    	}
		    	
		    	if (interval == 4)
		    		hour += 1;
		    	
		    	calendar.set(Calendar.HOUR_OF_DAY, hour - 1);
		    	calendar.set(Calendar.MINUTE, getMinutes(interval));
		    	calendar.set(Calendar.SECOND, second);
		    	
		    	System.out.println(calendar.getTime().toString());
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDate() {
		Date date = new Date();		
		String urlParameters = "?RTDTimestamp=" + date.getTime();
        URL url;
	    HttpURLConnection connection = null;  
		String urlString = "http://192.168.0.202/servlet/lmpUpdate";

	    try {	    	
	    	url = new URL(urlString);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/text");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");  
			connection.setUseCaches (false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			wr.writeBytes(urlParameters);
			wr.flush ();
			wr.close ();

		    InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer(); 
			      
			while((line = rd.readLine()) != null) {
			    response.append(line);
			    response.append('\r');
			}
			rd.close();
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();      
	    } 
	    finally {      
	    	if(connection != null) {    
	    		connection.disconnect();       
	    	}	    
	    }
	}
	
	  public static void testMain() {
	      HttpURLConnection connection = null;
	      OutputStreamWriter wr = null;
	      BufferedReader rd  = null;
	      StringBuilder sb = null;
	      String line = null;
	    
	      URL serverAddress = null;
	    
	      try {
	  		Date date = new Date();		
	        serverAddress = new URL("http://192.168.0.202/servlet/sppUpdate?intervalDate=" + date.getTime());	        
	          
	        connection = (HttpURLConnection)serverAddress.openConnection();      
	        connection.setRequestMethod("GET");
	        connection.setDoOutput(true);
	        connection.setReadTimeout(10000);
	        connection.connect();
	        
	        //get the output stream writer and write the output to the server
	        //not needed here since we are not POSTING
	        //wr = new OutputStreamWriter(connection.getOutputStream());
	        //wr.write("");
	        //wr.flush();
	        
	        rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        sb = new StringBuilder();
	        
	          while ((line = rd.readLine()) != null)
	          {
	              sb.append(line + '\n');
	          }
	        
	          System.out.println(sb.toString());
	                    
	      } catch (MalformedURLException e) {
	          e.printStackTrace();
	      } catch (ProtocolException e) {
	          e.printStackTrace();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	      finally
	      {
	          //close the connection, set all objects to null
	          connection.disconnect();
	          rd = null;
	          sb = null;
	          wr = null;
	          connection = null;
	      }
	  }
	

}