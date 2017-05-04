package com.softwarelikeyou.analyzer;

import java.util.Date;

import com.softwarelikeyou.pojo.SettlementPointType;

/**
 * @deprecated
 * Post workers are just too different at this time, so move methods to a Helper class if needed.
 */
public abstract class Post {
	
	protected SettlementPointType settlementPointType;
	protected Date reportDate;
	
	public void setSettlementPointType(SettlementPointType value) {
		settlementPointType = value;
	}
	
	public SettlementPointType getSettlementPointType() {
		return settlementPointType;
	}
	
	public void setReportDate(Date value) {
		reportDate = value;
	}
	
	public Date getReportDate() {
		return reportDate;
	}
	
	public abstract boolean execute();
	
}
