package com.softwarelikeyou.ercot.analyzer.rtspp;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;

import com.softwarelikeyou.pojo.ERCOTFile;
import com.softwarelikeyou.util.Util;

import org.apache.commons.beanutils.BeanUtils;

public class CSVSaver {

	private static Logger logger = Logger.getLogger(CSVSaver.class);
	
	private List<CSVPOJO> zeros = new ArrayList<CSVPOJO>();
	
	private ERCOTFile file;
	
	private static String PACKAGE = "com.softwarelikeyou.model.entity.rtspp";
	
	private Date intervalDate;

	public void setFile(ERCOTFile value) {
		file = value;
	}
	
	public ERCOTFile getFile() {
		return file;
	}
		
	public void setIntervalDate(Date value) {
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
			for (CSVPOJO row : rows) {

			    SettlementPoint settlementPoint = SettlementPointFacade.findById(row.getSettlementPointName());
			    if (settlementPoint != null)
			        row.setTemperature(settlementPoint.getTemperature());
			    
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
			    
			    row.setIntervalDate(calendar.getTime());
			    
			    intervalDate = row.getIntervalDate();
			    
    			String settlementPointType = row.getSettlementPointType().replaceAll("_", "");		
    			
    			String canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTSPP" + settlementPointType + "15M";
		        
    			RTSPPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
		        	   		        
		        if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0) {
			        canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTSPP" + settlementPointType + "24H";
			        RTSPPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
		        }
		        
    			switch (calendar.get(Calendar.MINUTE)) {
    			    case 0:
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTSPP" + settlementPointType + "30M";
    			        RTSPPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTSPP" + settlementPointType + "1H";
    			        RTSPPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	break;
    			    case 30:
    			    	canonical = PACKAGE + "." + settlementPointType.toLowerCase() + ".RTSPP" + settlementPointType + "30M";
    			        RTSPPFacade.createOrUpdate(getClass(canonical).cast(pojoToEntity(canonical, row)));
    			    	break;
    				default:
    					break;
    			}
    		
			    if (Util.toFloat(row.getSettlementPointPrice()) <= 0f) {
			    	zeros.add(row);			    
			    }	
			}
			
			//for (CSVPOJO zero : zeros)
				//logger.info("Zero price " + file.getUrl() + " - " + print(zero));
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		    logger.error(e.getCause(), e);
		    return false;
		}
	}
	
	//private String print(CSVPOJO pojo) {
//	    return pojo.getIntervalDate() + "," + pojo.getSettlementPointName() + "," + pojo.getSettlementPointPrice();	
//	}
	
	private Integer getMinutes(final Integer minute) throws RTSPPException {
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
	
	private Object pojoToEntity(String canonical, CSVPOJO pojo) throws RTSPPException {
		if (canonical == null || pojo == null) 
			throw new RTSPPException("Class name or Pojo cannot be empty");
		
		Object entity = null;
		try {
		    Class<?> classDefinition =  Class.forName(canonical);
            entity = classDefinition.newInstance();
            
            BeanUtils.setProperty(entity,"settlementPointName", pojo.getSettlementPointName());
            BeanUtils.setProperty(entity,"settlementPointType", pojo.getSettlementPointType());
            BeanUtils.setProperty(entity,"settlementPointPrice", Util.toFloat(pojo.getSettlementPointPrice()));
            BeanUtils.setProperty(entity,"DSTFlag", pojo.getDSTFlag());
            BeanUtils.setProperty(entity,"temperature", pojo.getTemperature());
            BeanUtils.setProperty(entity,"intervalDate", pojo.getIntervalDate());
	        
		}
		catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
			throw new RTSPPException(e);
		}
        return entity;
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends RTSPP> getClass(String value) throws ClassNotFoundException {
		return (Class<? extends RTSPP>) Class.forName(value);
	}
}
