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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.griddatabase.pojo.LMPCSV;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.util.ResourceUtil;
import com.softwarelikeyou.util.Util;
import com.softwarelikeyou.util.ZipUtil;

public class RTLMPCache {

	private static Logger logger = Logger.getLogger(RTLMPCache.class);
	
	private static String INTERVALENDING_FORMAT = "MM/dd/yyyy kk:mm:ss";	
	
	private static RTLMPCache instance = null;
			
	private static String directoryHome = "/home/ercot/data/rtlmp";
	
	private static String directory;
	
	private static long SECOND = 1000L;
	
	private static long MINUTE = SECOND * 60;
	
    private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	private RTLMPCache() {} 
	
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
				logger.error("LMP Cache", e);
			}
		}
	};
	
	private static Map<String, List<RTLMP>> map = Collections.synchronizedMap(new HashMap<String, List<RTLMP>>());
	
	private static Map<String, List<String>> settlementPoints = Collections.synchronizedMap(new HashMap<String, List<String>>());

	private static Map<String, RTLMP> latest = Collections.synchronizedMap(new HashMap<String, RTLMP>());

	static {
		settlementPoints.put("HU", new ArrayList<String>());
		settlementPoints.put("LZ", new ArrayList<String>());
	}
	
	public static boolean isStarted() {
		return start;
	}
	
	public static RTLMPCache getInstance() {
		if (instance == null || !start) {
			instance = new RTLMPCache();
	  		start();
		}
		return instance;
	}
	
	public static void start() {
		try {
			if (ResourceUtil.get().getString("lmp.watch.dir") != null)
				directoryHome = ResourceUtil.get().getString("lmp.watch.dir");
			if (directoryHome == null)
				throw new Exception("LMP Directory is invalid");
		    directory = directoryHome + "/" + yearFormat.format(new Date()) + "/" + monthFormat.format(new Date());
			initialize();
		    timer.schedule(task, 1 * MINUTE, 5 * MINUTE);
			start = true;
			logger.info("Start LMP Cache");
		}
		catch (Exception e) {
			logger.error("LMP Cache", e);
		}
	}
	
	public static void stop() {
		try {
			timer.cancel();
			start = false;
			map.clear();
		}
		catch (Exception e) {
			logger.error("LMP Cache", e);
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
					logger.info("LMP Cache: adding file " + p.getFileName().toString());
		            Path fullPath = FileSystems.getDefault().getPath(directory + "/" + p.getFileName());
		    	    map.put(p.getFileName().toString(), loadLMPS(fullPath));
			    }
			    catch (Exception e) {
			        logger.error("LMP Cache", e);
			    }
			}
		}
		catch (Exception e) {
			logger.error("LMP Cache", e);
		}
	}
	
	private static void initialize() {
		logger.info("Initialize LMP Cache from " + directory);
		retrieve();
	}
	
	private static void refresh() {
		logger.info("Refresh LMP Cache from " + directory);
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
    		logger.error("LMP Cache", e);
    	}
    	return results;
    }
    
    private static List<RTLMP> loadLMPS(final Path path) throws IOException, ParseException { 
		String contents = new String(ZipUtil.unzip(new ByteArrayInputStream(Files.readAllBytes(path))));
	    CSVReader reader = new CSVReader(new StringReader(contents), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
	    CsvToBean<LMPCSV> bean = new CsvToBean<LMPCSV>();
	    ColumnPositionMappingStrategy<LMPCSV> strat = new ColumnPositionMappingStrategy<LMPCSV>();
	    strat.setType(LMPCSV.class);
	    strat.setColumnMapping(LMPCSV.columns);
	    List<LMPCSV> rows = bean.parse(strat, reader);
	    List<RTLMP> lmps = new ArrayList<RTLMP>();
	    latest.clear();
	    for (LMPCSV row : rows) {
    	    RTLMP lmp = new RTLMP();
    	    lmp.setRTDTimestamp(Util.stringToDate(row.getRTDTimestamp(), INTERVALENDING_FORMAT));
    	    lmp.setRepeatedHourFlag(row.getRepeatedHourFlag());
    	    lmp.setLMP(Util.toFloat(row.getLMP()));
    	    lmp.setIntervalId(Integer.valueOf(row.getIntervalId()));
    	    lmp.setIntervalRepeatedHourFlag(row.getIntervalRepeatedHourFlag());
    	    lmp.setIntervalEnding(Util.stringToDate(row.getIntervalEndng(), INTERVALENDING_FORMAT));
    	    lmp.setSettlementPoint(row.getSettlementPoint());
    	    lmp.setSettlementPointType(row.getSettlementPointType());
    	    
    	    latest.put(lmp.getSettlementPoint(), lmp);
    	    
	        if (row.getSettlementPointType().startsWith("HU") || row.getSettlementPointType().startsWith("LZ")) {
	    	    lmps.add(lmp);
	    	    setSettlementPoint(lmp);
	        }
	    }
	    return lmps;
    }
    
	public static List<RTLMP> getList(final String settlementPoint) {
		List<RTLMP> results = new ArrayList<RTLMP>();
		for (String key : map.keySet()) {
			for (RTLMP lmp : map.get(key)) {
				if (lmp.getSettlementPoint().equals(settlementPoint)) {
					results.add(lmp);
				}
			}
		}
		return results;
	}
	
	public static List<String> getSettlementPoints(final String type) {
		return settlementPoints.get(type);
	}
	
	private static void setSettlementPoint(final RTLMP lmp) {
		if (settlementPoints.containsKey(lmp.getSettlementPointType())) {
			ArrayList<String> contents = (ArrayList<String>) settlementPoints.get(lmp.getSettlementPointType());
			if (!contents.contains(lmp.getSettlementPoint()))
					contents.add(lmp.getSettlementPoint());
		}
	}
	
	public static void clearMap() {
		map.clear();
	}
	
	@SuppressWarnings("unused")
	private static List<RTLMP> sorted(List<RTLMP> list) {
		Collections.sort(list, new Comparator<RTLMP>() {
			public int compare(RTLMP l1, RTLMP l2) {
				return l2.getIntervalEnding().compareTo(l1.getIntervalEnding());
			}
		});
		return list;
	}
	
	public static RTLMP getLatest(final String name) {
		return latest.get(name);
	}
}
