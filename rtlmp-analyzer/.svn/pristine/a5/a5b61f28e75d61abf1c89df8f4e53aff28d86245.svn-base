package com.softwarelikeyou.ercot.analyzer.rtlmp;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.exception.RTLMPException;
import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.pojo.ERCOTFile;
import com.softwarelikeyou.util.Util;

public class CSVSaver {
	
	private static Logger logger = Logger.getLogger(CSVSaver.class);
		
	private static String INTERVALENDING_FORMAT = "MM/dd/yyyy kk:mm:ss";	

	private List<CSVPOJO> zeros = new ArrayList<CSVPOJO>();

	private ERCOTFile file;
	
	private static String PACKAGE = "com.softwarelikeyou.model.entity.rtlmp";
	
	private Date RTDTimestamp;

	public void setFile(ERCOTFile value) {
		file = value;
	}
	
	public ERCOTFile getFile() {
		return file;
	}
	
	public void setRTDTimestamp(Date value) {
		RTDTimestamp = value;
	}
	
	public Date getRTDTimestamp() {
		return RTDTimestamp;
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
            for (CSVPOJO row : rows) {
            	
			    SettlementPoint settlementPoint = SettlementPointFacade.findById(row.getSettlementPoint());
			    if (settlementPoint != null)
			        row.setTemperature(settlementPoint.getTemperature());
			    
			    Date intervalEnding = Util.stringToDate(row.getIntervalEndng(), INTERVALENDING_FORMAT);

    			Calendar calendar = Calendar.getInstance();
    			calendar.setTime(intervalEnding);
    			    			
    			RTDTimestamp = Util.stringToDate(row.getRTDTimestamp(), INTERVALENDING_FORMAT);
    			
    			String settlementPointType = row.getSettlementPointType().replaceAll("_", "");
    			  
    			String canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "5M";
		        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
		        	   		        
		        if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0) {
			        canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "24H";
			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
		        }
		        
    			switch (calendar.get(Calendar.MINUTE)) {
    			    case 0:
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "15M";
    			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "30M";
    			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "1H";
    			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	break;
    			    case 15:
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "15M";
    			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	break;
    			    case 30:
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "15M";
    			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "30M";
    			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	break;
    			    case 45:
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTLMP" + settlementPointType + "15M";
    			        RTLMPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	break;
    				default:
    					break;
    			}
    			    
    			if (Util.toFloat(row.getLMP()) <= 0)
			    	zeros.add(row);			    
            }
            
			//for (CSVPOJO zero : zeros)
			    //logger.info("Zero price " + file.getUrl() + " - " + print(zero));
            return true;
        }
        catch (Exception e) {
        	e.printStackTrace();
        	logger.error(e);
            return false;
        }
	}
	
	@SuppressWarnings("unused")
	private String print(CSVPOJO pojo) {
	    return pojo.getIntervalEndng() + "," + pojo.getSettlementPoint() + "," + pojo.getLMP();	
	}
	
	private Object pojoToEntity(String canonical, CSVPOJO pojo) throws RTLMPException {
		if (canonical == null || pojo == null) 
			throw new RTLMPException("Class name or Pojo cannot be empty");
		
		Object entity = null;
		try {
		    Class<?> classDefinition =  Class.forName(canonical);
            entity = classDefinition.newInstance();

            BeanUtils.setProperty(entity,"RTDTimestamp", Util.stringToDate(pojo.getRTDTimestamp(), INTERVALENDING_FORMAT));
            BeanUtils.setProperty(entity,"repeatedHourFlag", pojo.getRepeatedHourFlag());
            BeanUtils.setProperty(entity,"LMP", Util.toFloat(pojo.getLMP()));
            BeanUtils.setProperty(entity,"intervalId", Integer.valueOf(pojo.getIntervalId()));
            BeanUtils.setProperty(entity,"intervalRepeatedHourFlag", pojo.getIntervalRepeatedHourFlag());
            BeanUtils.setProperty(entity,"intervalEnding", Util.stringToDate(pojo.getIntervalEndng(), INTERVALENDING_FORMAT));
            BeanUtils.setProperty(entity,"settlementPoint", pojo.getSettlementPoint());
            BeanUtils.setProperty(entity,"settlementPointType", pojo.getSettlementPointType());
            BeanUtils.setProperty(entity,"temperature", pojo.getTemperature());
		}
		catch (ParseException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
			throw new RTLMPException(e);
		}
        return entity;
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends RTLMP> getClass(String value) throws ClassNotFoundException {
		return (Class<? extends RTLMP>) Class.forName(value);
	}
}
