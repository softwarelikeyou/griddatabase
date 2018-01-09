package com.softwarelikeyou;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.util.HibernateUtil;

import junit.framework.TestCase;

public class LoadExcelASCPC extends TestCase {

	private final static int intervalDate = 0;
	private final static int NSPIN = 1;
	private final static int REGDN = 2;
	private final static int REGUP = 3;
	private final static int RRS = 4;
	 
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
 	        
 	    hibernateConfiguration.addAnnotatedClass(com.softwarelikeyou.model.entity.ascpc.ASCPC.class);

 	    hibernateConfiguration.setProperties(hibernateProperties);
 	    
     	HibernateUtil.getInstance(hibernateConfiguration);
     	
     	
 	}
 	
 	public void tearDown() throws Exception {
 	    HibernateUtil.shutdown();
 	}
 	
 	public void testCreate() {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null)
				System.out.println("session is null");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
    		if (session.isConnected())
    		    session.close();
		}
 	}
    
	public void testLoadASCPCs() {
		try {
			InputStream is = new FileInputStream("/Users/steve/Documents/ercot/ercot-unit-test/ASCPC7-3-12.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(is);
						
			for (int sheets = 0; sheets < workbook.getNumberOfSheets(); sheets++) {
				XSSFSheet sheet = workbook.getSheetAt(sheets); 

				for (int rows = 1; rows < sheet.getLastRowNum(); rows++ ) {
										
					XSSFRow row = sheet.getRow(rows);
					ASCPC ascpc = new ASCPC();
					for (int columns = 0; columns < row.getLastCellNum(); columns++) {
						XSSFCell cell = row.getCell(columns);
						switch (columns) {
						    case intervalDate:
						    	if (cell.getRawValue() == null) continue;
								ascpc.setIntervalDate(cell.getDateCellValue());
						    	break;
						    case NSPIN:
						    	ascpc.setNSPIN((float)cell.getNumericCellValue());
						    	break;
						    case REGDN:
								ascpc.setREGDN((float) cell.getNumericCellValue());
						    	break;
						    case REGUP:
								ascpc.setREGUP((float) cell.getNumericCellValue());
						    	break;
						    case RRS:
								ascpc.setRRS((float) cell.getNumericCellValue());
						    	break;
							default:
								break;
						}						
					}
					ASCPCFacade.createOrUpdate(ascpc);
				}
			}
			
			if (is != null)
		        is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
