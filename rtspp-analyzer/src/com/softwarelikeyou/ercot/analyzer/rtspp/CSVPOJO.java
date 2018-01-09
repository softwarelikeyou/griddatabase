package com.softwarelikeyou.ercot.analyzer.rtspp;

import java.util.Date;

public class CSVPOJO {
	public static String[] columns = { "deliveryDate", "deliveryHour", "deliveryInterval", "settlementPointName", "settlementPointType", "settlementPointPrice", "DSTFlag" };
	                                   
	private String deliveryDate;
	private String deliveryHour;
	private String deliveryInterval;
	private String settlementPointName;
	private String settlementPointType;
	private String settlementPointPrice;
	private String DSTFlag;
	private Integer temperature = -50;
	
	private Date intervalDate;
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(String value) {
		deliveryDate = value;
	}
	
	public String getDeliveryHour() {
		return deliveryHour;
	}
	
	public void setDeliveryHour(String value) {
		deliveryHour = value;
	}
	
	public String getDeliveryInterval() {
		return deliveryInterval;
	}
	
	public void setDeliveryInterval(String value) {
		deliveryInterval = value;
	}
	
	public String getSettlementPointName() {
		return settlementPointName;
	}
	
	public void setSettlementPointName(String value) {
		settlementPointName = value;
	}
	
	public String getSettlementPointType() {
		return settlementPointType;
	}
	
	public void setSettlementPointType(String value) {
		settlementPointType = value;
	}
	
	public String getSettlementPointPrice() {
		return settlementPointPrice;
	}
	
	public void setSettlementPointPrice(String value) {
		settlementPointPrice = value;
	}
	
	public void setDSTFlag(final String value) {
		DSTFlag = value;	
	}
	
	public String getDSTFlag() {
		return DSTFlag;
	}
	
	public void setTemperature(final Integer value) {
		this.temperature = value;	
	}
	
	public Integer getTemperature() {
		return this.temperature;
	}
	
	public void setIntervalDate(final Date value) {
		intervalDate = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}
}
