package com.softwarelikeyou.viewcontroller.converter;

import java.util.HashMap;
import java.util.Map;

import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;

public enum ByteUnit {

	BYTES(0, I18NStrings.BYTES, I18NStrings.BPS),
	KB(10, I18NStrings.KB, I18NStrings.KBPS),
	MB(20, I18NStrings.MB, I18NStrings.MBPS),
	GB(30, I18NStrings.GB, I18NStrings.GBPS),
	TB(40, I18NStrings.TB, I18NStrings.TBPS),
	PB(50, I18NStrings.PB, I18NStrings.PBPS),
	EB(60, I18NStrings.EB, I18NStrings.EBPS),
	ZB(70, I18NStrings.ZB, I18NStrings.ZBPS),
	YB(80, I18NStrings.YB, I18NStrings.YBPS);
	
	private static Map<String, ByteUnit> labelMap = new HashMap<String, ByteUnit>();
	
	static { 
		for( ByteUnit unit : ByteUnit.values() ) {
			labelMap.put(unit.getLabel(), unit);
			labelMap.put(unit.getPerSecondLabel(), unit);
		}
	}
	
	private String label;
	private String perSecondLabel;
	private double numberOfBytes; 
	
	private ByteUnit(int exp, String label, String perSecondLabel) {
		this.label          = label;
		this.numberOfBytes  = Math.pow(2, exp);
		this.perSecondLabel = perSecondLabel;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getPerSecondLabel() {
		return perSecondLabel;
	}
	
	public Double getNumberOfBytes() {
		return numberOfBytes;
	}
	
	public static ByteUnit fromLabel(String label) {
		
		ByteUnit selectedUnit = null;
		
		for( ByteUnit unit : ByteUnit.values() ) { 
			
			String formattedLabel = ELFunctions.getLabel(unit.getLabel());
			String formattedPSLabel = ELFunctions.getLabel(unit.getPerSecondLabel());
			
			if( formattedLabel.equals(label) || formattedPSLabel.equals(label) ) {
				selectedUnit = unit;
				break;
			}			
		}
		
		return selectedUnit;
	}
	
}
