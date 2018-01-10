package com.griddatabase.model.cache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.griddatabase.pojo.SPPCSV;
import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.util.ResourceUtil;
import com.softwarelikeyou.util.Util;
import com.softwarelikeyou.util.ZipUtil;

public class RTSPPCache {

	private static Logger logger = Logger.getLogger(RTSPPCache.class);
		
	private static RTSPPCache instance = null;
			
	private static String directoryHome = "/home/ercot/data/rtspp";
	
	private static String directory;
	
	private static long SECOND = 1000L;
	
	private static long MINUTE = SECOND * 60;
	
    private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	private RTSPPCache() {} 
	
	private static boolean start = false;

	private static Timer timer = new Timer();
	
	private static TimerTask task = new TimerTask() {
		@Override
		public void run() {
			try {
				refresh();
				logger.info("Map count is " + map.size());
			} 
			catch (Exception e) {
				logger.error("SPP Cache", e);
			}
		}
	};
	
	private static Map<String, List<RTSPP>> map = Collections.synchronizedMap(new HashMap<String, List<RTSPP>>());
	
	public static boolean isStarted() {
		return start;
	}
	
	public static RTSPPCache getInstance() {
		if (instance == null || !start) {
			instance = new RTSPPCache();
	  		start();
		}
		return instance;
	}
	
	public static void start() {
		try {
			if (ResourceUtil.get().getString("spp.watch.dir") != null)
				directoryHome = ResourceUtil.get().getString("spp.watch.dir");
			if (directoryHome == null)
				throw new Exception("SPP Directory is invalid");
		    directory = directoryHome + "/" + yearFormat.format(new Date()) + "/" + monthFormat.format(new Date());
			initialize();
		    timer.schedule(task, 1 * MINUTE, 5 * MINUTE);
			start = true;
			logger.info("Start SPP Cache");
		}
		catch (Exception e) {
			logger.error("SPP Cache", e);
		}
	}
	
	public static void stop() {
		try {
			timer.cancel();
			start = false;
		}
		catch (Exception e) {
			logger.error("SPP Cache", e);
		}
	}
	
	private static void retrieve() {
		try {
			Path path = FileSystems.getDefault().getPath(directory);
			DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*csv.zip");
			for (Path p : ds) {
                if (!validate(p))
                    continue;
			    try {
					logger.info("SPP Cache: adding file " + p.getFileName().toString());
		            Path fullPath = FileSystems.getDefault().getPath(directory + "/" + p.getFileName());
		    	    map.put(p.getFileName().toString(), loadSPPs(fullPath));
			    }
			    catch (Exception e) {
			        logger.error("SPP Cache", e);
			    }
			}
		}
		catch (Exception e) {
			logger.error("SPP Cache", e);
		}
	}
	
	private static void initialize() {
		logger.info("Initialize SPP Cache from " + directory);
		retrieve();
	}
	
	private static void refresh() {
		logger.info("Refresh SPP Cache from " + directory);
		retrieve();
	}
    
    private static boolean validate(final Path path) {
    	boolean results = true;
    	try {
            if (!path.getFileName().toString().endsWith("csv.zip"))
                return false;
 		    String timeStamp = path.getFileName().toString().substring(30, 38);
 			String today = dateFormat.format(new Date());
			if (!today.equals(timeStamp))
			    return false;
			if (map.containsKey(path.getFileName().toString()))
	    	    return false;
    	}
    	catch (Exception e) {
    		logger.error("SPP Cache", e);
    	}
    	return results;
    }
    
    private static List<RTSPP> loadSPPs(final Path path) throws IOException, ParseException { 
		String contents = new String(ZipUtil.unzip(new ByteArrayInputStream(Files.readAllBytes(path))));
	    CSVReader reader = new CSVReader(new StringReader(contents), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
	    CsvToBean<SPPCSV> bean = new CsvToBean<SPPCSV>();
	    ColumnPositionMappingStrategy<SPPCSV> strat = new ColumnPositionMappingStrategy<SPPCSV>();
	    strat.setType(SPPCSV.class);
	    strat.setColumnMapping(SPPCSV.columns);
	    List<SPPCSV> rows = bean.parse(strat, reader);
	    List<RTSPP> spps = new ArrayList<RTSPP>();
	    for (SPPCSV row : rows) {
	    	try {
	    	    RTSPP spp = new RTSPP();
	    	    spp.setSettlementPointName(row.getSettlementPointName());
	    	    spp.setSettlementPointType(row.getSettlementPointType());
	    	    spp.setSettlementPointPrice(Util.toFloat(row.getSettlementPointPrice()));
	    	    spp.setDSTFlag(row.getDSTFlag());
	    	    spp.setIntervalDate(getIntervalDate(row));
	    	    spps.add(spp);
	    	}
	    	catch (Exception e) {
	    		logger.error("RTSPP Cache", e);
	    	}
	    }
	    return spps;
    }
    
    private static Date getIntervalDate(final SPPCSV row) throws Exception {
    	Calendar calendar = Calendar.getInstance();
    	
	    try {
	    	
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
	    	
	    }
	    catch (ParseException e) {
		    throw new Exception("Delivery Date " + row.getDeliveryDate() + " is invalid", e);
	    }
	    catch (NumberFormatException e) {
	    	throw new Exception("Delivery Hour/Interval " + row.getDeliveryHour() + "/" + row.getDeliveryInterval() + " is invalid", e);
	    }
	    catch (RTSPPException e) {
	    	throw new Exception("Delivery Interval " + row.getDeliveryInterval() + " is invalid", e);
	    }
	    return calendar.getTime();
    }
    
	private static Integer getMinutes(final Integer minute) throws RTSPPException {
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
				throw new RTSPPException("Delivery Interval " + minute + " is invalid");
		}
	}
    
	public static Set<String> getNamesByType(String type) {
		Set<String> results = new HashSet<String>();
		for (String key : map.keySet()) {
			for (RTSPP spp : map.get(key)) {
				if (spp.getSettlementPointType().equals(type)) {
					results.add(spp.getSettlementPointName());
				}
			}
		}
		return results;
	}
	
	public static List<RTSPP> getList(final String settlementPoint) {
		List<RTSPP> results = new ArrayList<RTSPP>();
		for (String key : map.keySet()) {
			for (RTSPP spp : map.get(key)) {
				if (spp.getSettlementPointName().equals(settlementPoint)) 
					results.add(spp);
			}
		}
		return sorted(results);
	}
	
	public static List<RTSPP> getListByType(final String type) {
		List<RTSPP> results = new ArrayList<RTSPP>();
		for (String key : map.keySet()) {
			for (RTSPP spp : map.get(key)) {
				if (spp.getSettlementPointType().equals(type)) {
					results.add(spp);
				}
			}
		}
		return sorted(results);
	}
	
	public static RTSPP get(final Date date, final String settlementPointName) {
		for (String key : map.keySet()) {
			for (RTSPP spp : map.get(key)) {
				if (spp.getIntervalDate().equals(date) && spp.getSettlementPointName().equals(settlementPointName))
					return spp;
			}
		}
		return null;
	}
	
	public static void clearMap() {
		map.clear();
	}
	
	private static List<RTSPP> sorted(List<RTSPP> list) {
		Collections.sort(list, new Comparator<RTSPP>() {
			public int compare(RTSPP l1, RTSPP l2) {
				return l2.getIntervalDate().compareTo(l1.getIntervalDate());
			}
		});
		return list;
	}
}
