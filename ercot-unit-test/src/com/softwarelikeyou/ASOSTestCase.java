package com.softwarelikeyou;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.facade.SettlementPointTemperatureFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.SettlementPointTemperature;
import com.softwarelikeyou.util.HibernateUtil;

import junit.framework.TestCase;

public class ASOSTestCase extends TestCase {

	private static double EARTH_RADIUS = 3958.75;

    private Properties hibernateProperties = new Properties();
	 
	private Configuration hibernateConfiguration = new Configuration();
   
	public void setUp() throws Exception {
	    hibernateProperties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
	 	hibernateProperties.setProperty(Environment.URL, "jdbc:mysql://192.168.0.203/ercot");
	 	hibernateProperties.setProperty(Environment.USER, "root");
	 	hibernateProperties.setProperty(Environment.PASS, "dinky01");
	 	hibernateProperties.setProperty(Environment.SHOW_SQL, "false");
	 	hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
	 	hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
	 	        
	 	hibernateConfiguration.addAnnotatedClass(SettlementPoint.class);
 	    hibernateConfiguration.addAnnotatedClass(SettlementPointTemperature.class);

	 	hibernateConfiguration.setProperties(hibernateProperties);
	 	    
	    HibernateUtil.getInstance(hibernateConfiguration);
	}
	 	
	public void tearDown() throws Exception {
	    HibernateUtil.shutdown();
	}
	 
	private String getUrl(final String station) {
	    String url = "http://mesonet.agron.iastate.edu/cgi-bin//request/getData.py?ls_baseLayers=Google+Streets&TX_ASOS+Network=TX_ASOS+Network&station=[XXX]&data=tmpf&year1=2010&year2=2013&month1=1&month2=3&day1=1&day2=18&tz=local&format=comma&latlon=yes";
		return url.replace("[XXX]", station);
	}
	
	private String[] stations = { "ABI", "ALI", "E38", "AMA", "E11", "LBX", "GKY", "F44", "ATT", "EDC", "AUS", "BYY", "BMT",
	                              "BPT", "BEA", "BPG", "VAF", "BGD", "0F2", "BBD", "BBF", "BQX", "BKD", "11R", "XBP", "BRO",
	                              "BWD", "BMQ", "RWV", "T35", "HHF", "FTN", "CVB", "CDS", "LBR", "CPT", "6R3", "COM", "CLL",
	                              "MKN", "CXO", "CRP", "NGP", "CRS", "COT", "DKR", "DHT", "ADS", "DFW", "DAL", "RBD", "LUD",
	                              "DRT", "DTO", "FWS", "6R6", "DUX", "DYS", "EMK", "EBG", "ELP", "BKS", "BIF", "GRK", "FST",
	                              "AFW", "FTW", "NFW", "T82", "HLR", "GLE", "GVX", "GLS", "GOP", "GTU", "GYB", "JXI", "RPH",
	                              "GDJ", "GPM", "GVT", "GDP", "GUL", "MNZ", "HRL", "LHB", "HBV", "RFI", "F12", "HRX", "HQI",
	                              "INJ", "HDO", "HHV", "DZB", "LVJ", "MCJ", "DWH", "EFD", "TME", "SGR", "IAH", "AXH", "HOU", 
	                              "UTS", "TFP", "JSO", "JAS", "JCT", "SKF", "ERV", "CWC", "ILE", "NQI", "RYW", "3T5", "LZZ",
                                  "LNC", "LRD", "DLF", "AQO", "GGG", "LBB", "LFK", "MRF", "ASL", "MFE", "PWG", "TKI", "HQZ",
	                              "MDD", "MAF", "JWY", "JDD", "MWL", "OSA", "MIU", "MZG", "OCH", "BAZ", "OPM", "ODO", "ORG",
	                              "NOG", "OZA", "PSX", "PSN", "BPC", "PPA", "PRX", "PEQ", "PYX", "25T", "PVW", "PEZ", "RAS",
	                              "PIL", "PKV", "RND", "RBO", "RKP", "ECU", "F46", "RPE", "SJT", "5C1", "SAT", "SSF", "HYI",
	                              "GNC", "F39", "GYI", "SNK", "SOA", "SPL", "SEP", "SLR", "SWW", "TPL", "TRL", "TYR", "UVA",
	                              "F05", "VCT", "CNW", "ACT", "T65", "CRH", "ARM", "SPS", "INK", "APY", "VKY" };
			
	private List<String> getStations() {
	    return Arrays.asList(stations);
	}
	 
	public void testStation() {
        Session session = null;
	    HttpURLConnection connection = null;
			    
		try {
    		 
	        session = HibernateUtil.getSessionFactory().openSession();
	        if (session == null)
	            System.out.println("session is null");
	
	    	List<SettlementPoint> settlementPoints = SettlementPointFacade.findAll();
	    	
	    	for (String station : getStations()) {
	    	
	    		try {
	                
	    			URL url = new URL(getUrl(station));
		            connection = (HttpURLConnection) url.openConnection();
			        connection.setRequestMethod("GET");
			        connection.setDoInput (true);
			        connection.setDoOutput (true);
			        connection.setRequestProperty ("Content-Type", "text/csv");
			    
			        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.startsWith(station)) {
                	        String[] tokens = inputLine.split(",");
                	        //station, timestamp,        lon,      lat,     tmpf 
                	        //IAH,     2010-01-01 00:53, -95.5600, 30.0600, 46.94

                	        Date date = getDate(tokens[1]);
                	        if (date == null)
                		        continue;
                	        Calendar calendar = Calendar.getInstance();
                	        calendar.setTime(date);
                	        calendar.set(Calendar.MINUTE, 0);
                	        calendar.set(Calendar.SECOND, 0);
                	        calendar.set(Calendar.MILLISECOND, 0);
                	
                	        if (!isDouble(tokens[2]) || !isDouble(tokens[3]))
                		        continue;
                	        Double longitudeStart = Double.valueOf(tokens[2]);
                	        Double latitudeStart = Double.valueOf(tokens[3]);
                	        if (!isFloat(tokens[4]))
                		        continue;
                	        Float temperature = Float.valueOf(tokens[4]);
                	
                	        Long closet = 10000000L;
                	        SettlementPoint sp = null;
                	        for (SettlementPoint settlementPoint : settlementPoints) {
                		        if (!isDouble(settlementPoint.getLatitude()) || !isDouble(settlementPoint.getLongitude()))
                			        continue;
                		        Double latitudeEnd = Double.valueOf(settlementPoint.getLatitude());
                		        Double longitudeEnd = Double.valueOf(settlementPoint.getLongitude());
                		
                		        Long value = distance(latitudeStart, longitudeStart, latitudeEnd, longitudeEnd);
                		
                		        if (value < closet) {
                			        closet = value;
                			        sp = settlementPoint;
                		        }
                	        }
                	
                	        if (sp != null) {
                 	            SettlementPointTemperature spt = new SettlementPointTemperature();
                 	            spt.setDateTime(calendar.getTime());
                 	            spt.setSettlementPoint(sp.getName());
                 	            spt.setTemperature(Math.round(temperature));
                 	            SettlementPointTemperatureFacade.createOrUpdate(spt);
                		        System.out.println(calendar.getTime().toString() + "/" + sp.getName() + " is " + temperature);
                	        }
                        }
                    }
           
                    in.close();
                    connection.disconnect();
	    		}
	    		catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
    		if (session.isConnected())
    		    session.close();
		}
	}
	
	public long distance(double latitudeStart, double longitudeStart, double latitudeEnd, double longitudeEnd) throws Exception {
		double dLat = Math.toRadians(latitudeEnd-latitudeStart);
		double dLng = Math.toRadians(longitudeEnd-longitudeStart);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		           Math.cos(Math.toRadians(latitudeStart)) * Math.cos(Math.toRadians(latitudeEnd)) *
		           Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return (new Double(EARTH_RADIUS * c)).longValue();
	}	
	
	private static boolean isDouble(final String value) {
		boolean results = false;
		try {
			Double.valueOf(value);
			results = true;
		}
		catch (Exception e) {
			;
		}
		return results;
	}

	private static boolean isFloat(final String value) {
		boolean results = false;
		try {
			Float.valueOf(value);
			results = true;
		}
		catch (Exception e) {
			;
		}
		return results;
	}


	private static Date getDate(final String value) {
		Date results = null;
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		try {
			results = format.parse(value);
		}
		catch (Exception e) {
			;
		}
		return results;
	}
}
