package com.softwarelikeyou.collector.saver.csv;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.collector.pojo.Agg;
import com.softwarelikeyou.collector.pojo.Cleared;
import com.softwarelikeyou.collector.pojo.Self;
import com.softwarelikeyou.collector.saver.Saver;
import com.softwarelikeyou.exception.FileException;
import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.facade.H48DAMASAGOFFNSFacade;
import com.softwarelikeyou.facade.H48DAMASAGONNSFacade;
import com.softwarelikeyou.facade.H48DAMASAGREGDNFacade;
import com.softwarelikeyou.facade.H48DAMASAGREGUPFacade;
import com.softwarelikeyou.facade.H48DAMASAGRRSGNFacade;
import com.softwarelikeyou.facade.H48DAMASAGRRSLDFacade;
import com.softwarelikeyou.facade.H48DAMASAGRRSNCFacade;
import com.softwarelikeyou.facade.H48DAMASCSFacade;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.File.FileType;
import com.softwarelikeyou.model.entity.H48DAMASAGOFFNS;
import com.softwarelikeyou.model.entity.H48DAMASAGONNS;
import com.softwarelikeyou.model.entity.H48DAMASAGREGDN;
import com.softwarelikeyou.model.entity.H48DAMASAGREGUP;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSGN;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSLD;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSNC;
import com.softwarelikeyou.model.entity.H48DAMASCS;
import com.softwarelikeyou.util.Util;

public class H48DAMASCSVSaver extends Saver {

	private static Logger logger = Logger.getLogger(H48DAMASCSVSaver.class);
	
	public boolean saveAG(List<Map<String, String>> list) throws H48DAMASException {
		if (list == null)
			return false;
		try {
			for (Map<String, String> map : list) {
				String fileName = map.get("fileName");
        	    CSVReader reader = new CSVReader(new StringReader(map.get("contents")), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
        	    CsvToBean<Agg> bean = new CsvToBean<Agg>();
	            ColumnPositionMappingStrategy<Agg> strat = new ColumnPositionMappingStrategy<Agg>();
	            strat.setType(Agg.class);
	            strat.setColumnMapping(Agg.columms);
		        List<Agg> rows = bean.parse(strat, reader);
			    for (Agg row : rows) {
			        Date intervalDate = Util.stringToDate(row.getDeliveryDate() + " " + StringUtils.leftPad(row.getHourEnding(), 2, "0"), "MM/dd/yyyy HH");
				    Float offered = Saver.toFloat(row.getOffered());
				    Float price = Saver.toFloat(row.getPrice());

				    if (fileName.indexOf("OFFNS") > -1) {
					    H48DAMASAGOFFNS dam = new H48DAMASAGOFFNS();
					    dam.setIntervalDate(intervalDate);
				        dam.setOffered(offered);
				        dam.setPrice(price);
				        H48DAMASAGOFFNSFacade.createOrUpdate(dam);
				    }
			        if (fileName.indexOf("ONNS") > -1) {
					    H48DAMASAGONNS dam = new H48DAMASAGONNS();
					    dam.setIntervalDate(intervalDate);
				        dam.setOffered(offered);
				        dam.setPrice(price);
					    H48DAMASAGONNSFacade.createOrUpdate(dam);
				    }
				    if (fileName.indexOf("REGDN") > -1) {
					    H48DAMASAGREGDN dam = new H48DAMASAGREGDN();
					    dam.setIntervalDate(intervalDate);
				        dam.setOffered(offered);
				        dam.setPrice(price);
					    H48DAMASAGREGDNFacade.createOrUpdate(dam);
				    }
				    if (fileName.indexOf("REGUP") > -1) {
					    H48DAMASAGREGUP dam = new H48DAMASAGREGUP();
					    dam.setIntervalDate(intervalDate);
				        dam.setOffered(offered);
				        dam.setPrice(price);
					    H48DAMASAGREGUPFacade.createOrUpdate(dam);
				    }
				    if (fileName.indexOf("RRSGN") > -1) {
					    H48DAMASAGRRSGN dam = new H48DAMASAGRRSGN();
					    dam.setIntervalDate(intervalDate);
				        dam.setOffered(offered);
				        dam.setPrice(price);
					    H48DAMASAGRRSGNFacade.createOrUpdate(dam);
				    }
				    if (fileName.indexOf("RRSLD") > -1) {
					    H48DAMASAGRRSLD dam = new H48DAMASAGRRSLD();
					    dam.setIntervalDate(intervalDate);
				        dam.setOffered(offered);
				        dam.setPrice(price);
					    H48DAMASAGRRSLDFacade.createOrUpdate(dam);
				    }
				    if (fileName.indexOf("RRSNC") > -1) {
					    H48DAMASAGRRSNC dam = new H48DAMASAGRRSNC();
					    dam.setIntervalDate(intervalDate);
				        dam.setOffered(offered);
				        dam.setPrice(price);
					    H48DAMASAGRRSNCFacade.createOrUpdate(dam);
				    }
			    }
			    saveFile(fileName, map.get("url"));
			}
			return true;
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}
	}

	private void saveFile(String fileName, String url) throws FileException {
		File file = new File();
	    file.setName(fileName);
	    file.setDownloaded(new Date());
	    file.setUrl(url);
	    file.setMimeType(File.MimeType.CSV);
	    file.setType(FileType.H48DAMAS);
	    file = FileFacade.createOrUpdate(file);
        logger.info("Saving  file " + fileName);    
	}
	
	public boolean saveCS(List<Map<String, String>> list) throws H48DAMASException {
		if (list == null)
			return false;
		try {
			for (Map<String, String> map : list) {
        	    CSVReader reader = new CSVReader(new StringReader(map.get("contents")), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
        	    String fileName = map.get("fileName");
        	    
			    if (fileName.startsWith("48h_Cleared")) {
	                CsvToBean<Cleared> bean = new CsvToBean<Cleared>();
	                ColumnPositionMappingStrategy<Cleared> strat = new ColumnPositionMappingStrategy<Cleared>();
	                strat.setType(Cleared.class);
	                strat.setColumnMapping(Cleared.columms);
				    List<Cleared> rows = bean.parse(strat, reader);
				    for (Cleared row : rows) {
					    Date intervalDate = Util.stringToDate(row.getDeliveryDate() + " " + StringUtils.leftPad(row.getHourEnding(), 2, "0"), "MM/dd/yyyy HH");
					    Float total = Saver.toFloat(row.getTotal());
					    H48DAMASCS dam = H48DAMASCSFacade.findById(intervalDate);
					    if (dam == null) {
					        dam = new H48DAMASCS();
					        dam.setIntervalDate(intervalDate);
					    }
				        if (fileName.indexOf("NSPIN") > -1)
				    	    dam.setClearedNspin(total);
				        if (fileName.indexOf("REGDN") > -1)
				    	    dam.setClearedRegdn(total);
				        if (fileName.indexOf("REGUP") > -1) 
				    	    dam.setcClearedRegup(total);
				        if (fileName.indexOf("RRSGEN") > -1)
				    	    dam.setClearedRssgen(total);
				        if (fileName.indexOf("RRSLOAD") > -1) 
				    	    dam.setClearedRrsload(total);   
					    H48DAMASCSFacade.createOrUpdate(dam);
				    }    
			    }
			
		   	    if (fileName.startsWith("48h_Self")) {
	                CsvToBean<Self> bean = new CsvToBean<Self>();
	                ColumnPositionMappingStrategy<Self> strat = new ColumnPositionMappingStrategy<Self>();
	                strat.setType(Self.class);
	                strat.setColumnMapping(Self.columms);
				    List<Self> rows = bean.parse(strat, reader);
				    for (Self row : rows) {
					    Date intervalDate = Util.stringToDate(row.getDeliveryDate() + " " + StringUtils.leftPad(row.getHourEnding(), 2, "0"), "MM/dd/yyyy HH");
					    Float total = Saver.toFloat(row.getTotal());
					    H48DAMASCS dam = H48DAMASCSFacade.findById(intervalDate);
					    if (dam == null) {
					        dam = new H48DAMASCS();
					        dam.setIntervalDate(intervalDate);
					    } 
				        if (fileName.indexOf("NSPIN") > -1)
				    	    dam.setSelfArrangedNspin(total);
				        if (fileName.indexOf("REGDN") > -1)
				    	    dam.setSelfArrangedRegdn(total);
				        if (fileName.indexOf("REGUP") > -1) 
				    	    dam.setcSelfArrangedRegup(total);
				        if (fileName.indexOf("RRSGEN") > -1)
				    	    dam.setSelfArrangedRssgen(total);
				        if (fileName.indexOf("RRSLOAD") > -1) 
				    	    dam.setSelfArrangedRrsload(total);   
					    H48DAMASCSFacade.createOrUpdate(dam);
				    }
			    }
			    saveFile(fileName, map.get("url"));
			}
			return true;
		}
		catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}
	}
}
