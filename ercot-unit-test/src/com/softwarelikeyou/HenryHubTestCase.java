package com.softwarelikeyou;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.facade.HenryHubFacade;
import com.softwarelikeyou.model.entity.HenryHub;
import com.softwarelikeyou.util.HibernateUtil;

import junit.framework.TestCase;

public class HenryHubTestCase extends TestCase {

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
	 	        
	 	 hibernateConfiguration.addAnnotatedClass(HenryHub.class);

	 	 hibernateConfiguration.setProperties(hibernateProperties);
	 	    
	     HibernateUtil.getInstance(hibernateConfiguration);
	 }
	 	
	 public void tearDown() throws Exception {
	 	 HibernateUtil.shutdown();
	 }

	private static String xls = "http://www.eia.gov/dnav/ng/hist_xls/RNGWHHDd.xls";
	
	public void testInsert() {

	    HttpURLConnection connection = null;
 		Session session = null;
 		POIFSFileSystem fileSystem = null;
 		
	    try {
		    URL url = new URL(xls);
		    connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setDoInput (true);
		    connection.setDoOutput (true);
		    connection.setRequestProperty ("Content-Type","application/vnd.ms-excel");
		     		 
	 		session = HibernateUtil.getSessionFactory().openSession();
			if (session == null)
			    System.out.println("session is null");
			
		    fileSystem = new POIFSFileSystem(connection.getInputStream());
		      
		    HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
		      
		    HSSFSheet sheet = workBook.getSheetAt(1);
		      
		    for (int row = sheet.getFirstRowNum() + 3; row <= sheet.getLastRowNum(); row++) {
		    	try {
		            Date date = sheet.getRow(row).getCell(0).getDateCellValue();
		            if (date == null)
		            	continue;
		    	    Float price = (float) sheet.getRow(row).getCell(1).getNumericCellValue();
		    	    if (price == null)
		    	    	continue;
		    	    
		            HenryHub hh = new HenryHub();
		    	    hh.setDate(date);
		    	    hh.setPrice(price);
		            HenryHubFacade.createOrUpdate(hh);
		    	}
		    	catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
    		if (session.isConnected())
    		    session.close();
    		if (connection != null)
    			connection.disconnect();
		}
	}
	
	public void testPrint() {

	    HttpURLConnection urlConn = null;
	    Vector cellVectorHolder = new Vector();

	    try {
		    URL url = new URL(xls);
		    urlConn = (HttpURLConnection)url.openConnection();
		    urlConn.setRequestMethod("GET");
		    urlConn.setDoInput (true);
		    urlConn.setDoOutput (true);
		    urlConn.setRequestProperty ("Content-Type","application/x-www-form-urlencoded");
		     		 			
		    POIFSFileSystem myFileSystem = new POIFSFileSystem(urlConn.getInputStream());
		      
		    HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
		      
		    HSSFSheet mySheet = myWorkBook.getSheetAt(1);
		      
		    Iterator rowIter = mySheet.rowIterator();
		      		      
		    while(rowIter.hasNext()) {
		        HSSFRow myRow = (HSSFRow) rowIter.next();
		        Iterator<?> cellIter = myRow.cellIterator();
		        if (myRow.getRowNum() < 3)
		            continue;
		        Vector cellStoreVector= new Vector();
		         
		        while(cellIter.hasNext()) {
		            HSSFCell myCell = (HSSFCell) cellIter.next();
		            cellStoreVector.addElement(myCell);
		        }
		        cellVectorHolder.addElement(cellStoreVector);
		     }
		      
		     printCellDataToConsole(cellVectorHolder); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printCellDataToConsole(Vector dataHolder) {
		 
        for (int i=0;i<dataHolder.size();i++) {                  
                      Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
            for (int j=0; j< cellStoreVector.size();j++){
                HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
                String stringCellValue = myCell.toString();
                System.out.print(stringCellValue+"\t");
            }
            System.out.println();
        }
    }
}
