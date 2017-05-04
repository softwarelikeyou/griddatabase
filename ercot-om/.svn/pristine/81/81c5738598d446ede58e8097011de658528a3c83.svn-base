package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="settlementPointTemperature", uniqueConstraints = @UniqueConstraint(columnNames = { "settlementPoint", "dateTime" }))
public class SettlementPointTemperature implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="settlementPoint")
	private String settlementPoint;
	
	public String getSettlementPoint() {
		return settlementPoint;
	}
	
	public void setSettlementPoint(final String settlementPoint) {
		this.settlementPoint = settlementPoint;
	}
	
	@Id
	@Column(name="dateTime")
	private Date dateTime;
	
	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(final Date dateTime) {
		this.dateTime = dateTime;
	}
	
	@Column(name="temperature", columnDefinition = "int(3) default -50")
	private Integer temperature = -50;	
	
	public Integer getTemperature() {
		return temperature;
	}
	
	public void setTemperature(final Integer value) {
		temperature = value;
	}

	@Override
	public String toString() {
		return "SettlementPointTemperature [settlementPoint=" + settlementPoint
				+ ", dateTime=" + dateTime + ", temperature=" + temperature
				+ "]";
	}
	
	
}
