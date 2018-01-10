package com.griddatabase.pojo;

import java.util.Date;

public class LMPCSV {

	public static String[] columns = { "RTDTimestamp", "repeatedHourFlag", "intervalId", "intervalEnding", "intervalRepeatedHourFlag", "settlementPoint", "settlementPointType", "LMP"};
	
	private String RTDTimestamp;
	private String repeatedHourFlag;	
	private String intervalId;
	private String intervalEnding;
	private String intervalRepeatedHourFlag;
	private String settlementPoint;
	private String settlementPointType;
	private String LMP;	
	private Integer temperature;
	private Date reportDate;

	public String getRTDTimestamp() {
		return RTDTimestamp;
	}

	public void setRTDTimestamp(String value) {
		this.RTDTimestamp = value;
	}
	
	public void setRepeatedHourFlag(final String value) {
		this.repeatedHourFlag = value;	
	}
	
	public String getRepeatedHourFlag() {
		return this.repeatedHourFlag;
	}
	
	public void setIntervalId(final String value) {
		this.intervalId = value;	
	}
	
	public String getIntervalId() {
		return this.intervalId;
	}
	
	public void setIntervalEnding(final String value) {
		this.intervalEnding = value;	
	}
	
	public String getIntervalEndng() {
		return this.intervalEnding;
	} 
	
	public void setIntervalRepeatedHourFlag(final String value) {
		this.intervalRepeatedHourFlag = value;	
	}
	
	public String getIntervalRepeatedHourFlag() {
		return this.intervalRepeatedHourFlag;
	}
	
	public void setSettlementPoint(final String value) {
		this.settlementPoint = value;	
	}
	
	public String getSettlementPoint() {
		return this.settlementPoint;
	}
	
	public void setSettlementPointType(final String value) {
		this.settlementPointType = value;	
	}
	
	public String getSettlementPointType() {
		return this.settlementPointType;
	}
	
	public void setLMP(final String value) {
		this.LMP = value;	
	}
	
	public String getLMP() {
		return this.LMP;
	}
	
	public void setTemperature(final Integer value) {
		this.temperature = value;	
	}
	
	public Integer getTemperature() {
		return this.temperature;
	}
	
	public void setReportDate(final Date value) {
		reportDate = value;
	}
	
	public Date getReportDate() {
		return reportDate;
	}
}
