package com.softwarelikeyou.collector.saver.csv;

import java.io.StringReader;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.collector.saver.Saver;
import com.softwarelikeyou.facade.H48DAMHPFacade;
import com.softwarelikeyou.model.entity.H48DAMHP;
import com.softwarelikeyou.pojo.H48DAMHPCSV;
import com.softwarelikeyou.util.Util;

public class H48DAMHPCSVSaver extends Saver {

	private static Logger logger = Logger.getLogger(H48DAMHPCSVSaver.class);

	@Override
	public boolean execute() {
		if (map == null)
			return false;
		try {
        	CSVReader reader = new CSVReader(new StringReader(map.get("contents")), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
            CsvToBean<H48DAMHPCSV> bean = new CsvToBean<H48DAMHPCSV>();
            ColumnPositionMappingStrategy<H48DAMHPCSV> strat = new ColumnPositionMappingStrategy<H48DAMHPCSV>();
            strat.setType(H48DAMHPCSV.class);
            strat.setColumnMapping(H48DAMHPCSV.columns);
			List<H48DAMHPCSV> rows = bean.parse(strat, reader);
			for (H48DAMHPCSV row : rows) {
				H48DAMHP dam = new H48DAMHP();
				dam.setIntervalDate(Util.stringToDate(row.getDeliveryDate() + " " + StringUtils.leftPad(row.getHourEnding(), 2, "0"), "dd-MMM-yy HH"));
				dam.setAsType(row.getASType());
				dam.setBlockIndicator(row.getBlockIndicator());
				dam.setOfferedPrice(Saver.toFloat(row.getOfferedPrice()));
				dam.setOfferedQuantity(toFloat(row.getOfferedQuantity()));
				dam.setResourceName(row.getResourceName());				
				H48DAMHPFacade.createOrUpdate(dam);
			}
			return true;
		}
		catch (Exception e) {
		    logger.error(e);
		    return false;
		}
	}

}
