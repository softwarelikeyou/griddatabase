package com.griddatabase.ascpc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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

public class LoadFile {
	
	public static void main(String[] args) {
		if (args.length != 2) {
		    System.out.println("Usage: LoadFile type file");
		}
		
		try {
		    switch (args[0]) {
	            case "ascpc2000to2012":
	        	    hibernate();
	    	        ascpc2004to2010(args[1]);
		            break;
		        case "ascpc2011":
		        	hibernate();
		    	    ascpc2011(args[1]);
			        break;
		        case "rtspp":
		      	    break;
			    default:
				    System.out.println("Invalid file type");
				    break;
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
	 	    HibernateUtil.shutdown();
		}
	}
	
	public static void hibernate() {
	    Properties hibernateProperties = new Properties();
	    Configuration hibernateConfiguration = new Configuration();
 	    hibernateProperties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
 	    hibernateProperties.setProperty(Environment.URL, "jdbc:mysql://192.168.0.203/ercot");
 	    hibernateProperties.setProperty(Environment.USER, "root");
 	    hibernateProperties.setProperty(Environment.PASS, "dinky01");
 	    hibernateProperties.setProperty(Environment.SHOW_SQL, "false");
 	    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
 	    hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
 	    hibernateConfiguration.addAnnotatedClass(ASCPC.class);
 	    hibernateConfiguration.setProperties(hibernateProperties);
     	HibernateUtil.getInstance(hibernateConfiguration);
     	
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (session == null)
			System.out.println("session is null");
		session.close();
	}
	
	public static void ascpc2004to2010(final String file) {
		if (file == null)
			return;
		
		final int intervalDate = 0;
		final int NSPIN = 1;
		final int REGDN = 2;
		final int REGUP = 3;
		final int RRS = 4;
		try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(is);
						
			for (int sheets = 0; sheets < workbook.getNumberOfSheets(); sheets++) {
				XSSFSheet sheet = workbook.getSheetAt(sheets); 

				for (int rows = 1; rows <= sheet.getLastRowNum(); rows++ ) {
										
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
					ascpc.setIntervalDate(getIntervalDate(ascpc));
					//ASCPCFacade.createOrUpdate(ascpc);
					print(ascpc);
				}
			}
			
			if (is != null)
		        is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static void ascpc2011(final String file) {
		if (file == null)
			return;
		
		final int intervalDate = 0;
		final int hourEnding = 1;
		final int NSPIN = 2;
		final int REGDN = 3;
		final int REGUP = 4;
		final int RRS = 5;
		
		try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(is);
						
			for (int sheets = 0; sheets < workbook.getNumberOfSheets(); sheets++) {
				XSSFSheet sheet = workbook.getSheetAt(sheets); 
				 				
				for (int rows = 2; rows <= sheet.getLastRowNum(); rows++ ) {
										
					XSSFRow row = sheet.getRow(rows);
				
					ASCPC ascpc = new ASCPC();
					for (int columns = 0; columns < row.getLastCellNum(); columns++) {
						XSSFCell cell = row.getCell(columns);
						
						switch (columns) {
						    case intervalDate:
						    	if (cell.getRawValue() == null || !HSSFDateUtil.isCellDateFormatted(cell)) continue;
						        ascpc.setIntervalDate(cell.getDateCellValue());
						    	break;
						    case hourEnding:
						    	Integer hour;
					    	    Calendar calendar = Calendar.getInstance();
								if (sheet.getSheetName().equals("January")) {
							    	if (cell.getRawValue() == null) continue;
							    	String[] tokens = cell.getStringCellValue().split(":");
							    	if (tokens[0].substring(1, 2).equals("0"))
							    	    hour = Integer.valueOf(tokens[0].replace("0", "").trim());
							    	else
							    		hour = Integer.valueOf(tokens[0].trim());
								}
								else {
						    	    if (cell.getRawValue() == null) continue;
						    	    hour = (int) cell.getNumericCellValue();
								}
					    	    calendar.setTime(ascpc.getIntervalDate());
					    	    calendar.set(Calendar.HOUR_OF_DAY, hour);
					    	    ascpc.setIntervalDate(calendar.getTime());
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
					if (ascpc.getIntervalDate().equals(new Date())) continue;
					ASCPCFacade.createOrUpdate(ascpc);
					print(ascpc);
				}
			}
			
			if (is != null)
		        is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private static Date getIntervalDate(final ASCPC ascpc) {
		Calendar calendar = Calendar.getInstance();
		Integer hour;
		try {
			calendar.setTime(ascpc.getIntervalDate());
		    String tokens[] = ascpc.getHourEnding().split(":");
		    hour = Integer.valueOf(tokens[0]);
		    if (hour != null)
		    	calendar.set(Calendar.HOUR, hour);
		    
		}
		catch (Exception e) {
			;
		}
		return calendar.getTime();
	}
	
	private static void print(final ASCPC ascpc) {
		System.out.println(ascpc.getIntervalDate() + "," + 
	                       ascpc.getNSPIN() + "," + 
				           ascpc.getREGDN() + "," + 
	                       ascpc.getREGUP() + "," + 
				           ascpc.getRRS());
	}
}
