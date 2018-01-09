
package com.softwarelikeyou.test;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.softwarelikeyou.model.HibernateUtil;
import com.softwarelikeyou.model.dao.ZipCodeDAO;
import com.softwarelikeyou.model.entity.ZipCode;

    public class CSVTest extends TestCase {
 
    private Properties properties = new Properties();
 
    @SuppressWarnings("unused")
    private SessionFactory sessionFactory;
 
    public void setUp() throws Exception {
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.URL, "jdbc:mysql://www.softwarelikeyou.com/weather");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "dinky01");
        properties.setProperty(Environment.SHOW_SQL, "false");
        properties.setProperty(Environment.HBM2DDL_AUTO, "validate");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
    }
 
    public void tearDown() throws Exception {
        HibernateUtil.shutdown();
    }
    
	@SuppressWarnings("static-access")
	public void testZipCode() {
        try {
            sessionFactory = HibernateUtil.getInstance(properties).getSessionFactory();

        	CSVReader reader = new CSVReader(new FileReader("/Users/steve/Documents/workspace/ercot/countyzipCode.csv"), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 0);
            CsvToBean<ZipCode> bean = new CsvToBean<ZipCode>();
            ColumnPositionMappingStrategy<ZipCode> strat = new ColumnPositionMappingStrategy<ZipCode>();
            strat.setType(ZipCode.class);
            String[] columns = { "name", "county" };
            strat.setColumnMapping(columns);
			List<ZipCode> rows = bean.parse(strat, reader);
            for (ZipCode row : rows) {
            	ZipCodeDAO.getInstance().createOrUpdate(row);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}