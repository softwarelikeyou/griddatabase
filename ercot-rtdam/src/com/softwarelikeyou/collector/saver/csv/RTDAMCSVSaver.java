package com.softwarelikeyou.collector.saver.csv;

import java.io.StringReader;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.collector.saver.Saver;
import com.softwarelikeyou.facade.RTDAMFacade;
import com.softwarelikeyou.model.entity.RTDAM;

public class RTDAMCSVSaver extends Saver {

	private static Logger logger = Logger.getLogger(RTDAMCSVSaver.class);

	@Override
	public boolean execute() {
		if (map == null)
			return false;
        try {
        	CSVReader reader = new CSVReader(new StringReader(map.get("contents")), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
            CsvToBean<RTDAMCSV> bean = new CsvToBean<RTDAMCSV>();
            ColumnPositionMappingStrategy<RTDAMCSV> strat = new ColumnPositionMappingStrategy<RTDAMCSV>();
            strat.setType(RTDAMCSV.class);
            strat.setColumnMapping(RTDAMCSV.columns);
			List<RTDAMCSV> rows = bean.parse(strat, reader);
            for (RTDAMCSV row : rows) {
            	RTDAM rtdam = new RTDAM();
        		rtdam.setSettlementPointName(row.getSettlementPoint());
        		rtdam.setSettlementPointPrice(toFloat(row.getSettlementPointPrice()));
        		rtdam.setDSTFlag(row.getDSTFlag());
                try {
        			rtdam.setIntervalDate(getIntervalDateTime(row.getDeliveryDate() + " " + row.getHourEnding()));
        		} 
                catch (ParseException e) {
                	logger.error(e);
                	continue;
        		}
                RTDAMFacade.createOrUpdate(rtdam);
            }
            return true;
        }
        catch (Exception e) {
            logger.error(e);
            return false;
        }
	}
}
