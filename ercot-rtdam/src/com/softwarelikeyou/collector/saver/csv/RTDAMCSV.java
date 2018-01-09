package com.softwarelikeyou.collector.saver.csv;

public class RTDAMCSV {
	public static String[] columns = { "deliveryDate", "hourEnding", "settlementPoint", "settlementPointPrice", "DSTFlag" };

	private String deliveryDate;
	private String hourEnding;
	private String settlementPoint;
	private String settlementPointPrice;
	private String DSTFlag;
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(String value) {
		this.deliveryDate = value;
	}
	
	public String getHourEnding() {
		return hourEnding;
	}
	
	public void setHourEnding(String value) {
		this.hourEnding = value;
	}
	
	public void setSettlementPoint(String value) {
		this.settlementPoint = value;	
	}
	
	public String getSettlementPoint() {
		return this.settlementPoint;
	}
	
	public void setSettlementPointPrice(String value) {
		this.settlementPointPrice = value;	
	}
	
	public String getSettlementPointPrice() {
		return this.settlementPointPrice;
	}
	
	public void setDSTFlag(String value) {
		this.DSTFlag= value;	
	}
	
	public String getDSTFlag() {
		return this.DSTFlag;
	}
}
