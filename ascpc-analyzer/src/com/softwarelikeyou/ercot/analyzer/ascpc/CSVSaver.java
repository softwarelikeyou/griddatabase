package com.softwarelikeyou.ercot.analyzer.ascpc;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.pojo.ERCOTFile;
import com.softwarelikeyou.util.Util;

public class CSVSaver {

	private static Logger logger = Logger.getLogger(CSVSaver.class);

	private static String DATEFORMAT = "MM/dd/yyyy kk:mm";

	private ERCOTFile file;
	
	private Date intervalDate;
	
	public void setFile(ERCOTFile value) {
		file = value;
	}
	
	public ERCOTFile getFile() {
		return file;
	}
	
	public void setIntervalDate(final Date value) {
		intervalDate = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}
		
	public boolean execute() {
		if (file == null)
			return false;
		try {
        	CSVReader reader = new CSVReader(new StringReader(file.getContent()), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
            CsvToBean<CSVPOJO> bean = new CsvToBean<CSVPOJO>();
            ColumnPositionMappingStrategy<CSVPOJO> strat = new ColumnPositionMappingStrategy<CSVPOJO>();
            strat.setType(CSVPOJO.class);
            strat.setColumnMapping(CSVPOJO.columns);
			List<CSVPOJO> rows = bean.parse(strat, reader);
			Map<String, ASCPC> map = new HashMap<String, ASCPC>();
			for (CSVPOJO row : rows)
            	map.put(row.getDeliveryDate() + " " + row.getHourEnding(), new ASCPC());
			for (CSVPOJO row : rows) {
				String key = row.getDeliveryDate() + " " + row.getHourEnding();
				ASCPC ascpc = map.get(key);
				ascpc.setIntervalDate(Util.stringToDate(row.getDeliveryDate() + " " + row.getHourEnding(), DATEFORMAT));    		    
				Field field = ascpc.getClass().getDeclaredField(row.getAncillaryType());
    		    field.setAccessible(true);
    		    field.set(ascpc, Util.toFloat(row.getMCPC()));
    		    map.put(key, ascpc);
    		    if (row.getHourEnding().equals("01:00"))
				    setIntervalDate(ascpc.getIntervalDate());
			}
			for (String key : map.keySet()) {
				ASCPC ascpc = map.get(key);
				ASCPCFacade.createOrUpdate(ascpc);
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		    logger.error(e.getCause(),e);
		    return false;
		}
	}
}
