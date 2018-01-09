package com.softwarelikeyou.util;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	public static HSSFCellStyle getFullBorderStyle(HSSFWorkbook workbook, final HSSFFont font, final boolean isCenter) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderTop((short) 1);
		style.setBorderBottom((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderLeft((short) 1);
		style.setFont(font);
		if (isCenter)
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		else
		    style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		return style;
	}
	
	public static HSSFFont getFont(HSSFWorkbook workbook, final int size, final boolean isBold) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) size);
        if (isBold)
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    return font;
	}
	
	public static void deleteASCPCs() {
		File dir = new File(".");
		FileFilter fileFilter = new WildcardFileFilter("ASCPC*");
		File[] files = dir.listFiles(fileFilter);
		for (int i = 0; i < files.length; i++) {
		   files[i].delete();
		}
	}
	
	public static void deleteRTDAMs() {
		File dir = new File(".");
		FileFilter fileFilter = new WildcardFileFilter("RTDAM*");
		File[] files = dir.listFiles(fileFilter);
		for (int i = 0; i < files.length; i++) {
		   files[i].delete();
		}
	}
	
	public static void deleteRTSPPs() {
		File dir = new File(".");
		FileFilter fileFilter = new WildcardFileFilter("RTSPP*");
		File[] files = dir.listFiles(fileFilter);
		for (int i = 0; i < files.length; i++) {
		   files[i].delete();
		}
	}
	
	public static void deleteH48DAMHPs() {
		File dir = new File(".");
		FileFilter fileFilter = new WildcardFileFilter("H48DAMHP*");
		File[] files = dir.listFiles(fileFilter);
		for (int i = 0; i < files.length; i++) {
		   files[i].delete();
		}
	}
}
