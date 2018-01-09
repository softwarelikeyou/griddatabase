package com.softwarelikeyou.collector.pojo;

public class Self {

	private String deliveryDate;
	private String hourEnding;
	private String total;
	
	public static String[] columms = { "deliveryDate", "hourEnding", "total" };
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(String value) {
		deliveryDate = value;
	}
	
	public String getHourEnding() {
		return hourEnding;
	}
	
	public void setHourEnding(String value) {
		hourEnding = value;
	}
	
	public String getTotal() {
		return total;
	}
	
	public void setTotal(String value) {
		total = value;
	}
}