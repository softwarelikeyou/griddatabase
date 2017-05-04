package com.softwarelikeyou.model.entity.rtspp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

@MappedSuperclass
public class RTSPP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="intervalDate")
	@XmlElement(name="intervalDate")
	private Date intervalDate = new Date();
	
	@Id
	@Column(name="settlementPointName", length=25)
	@XmlElement(name="settlementPointName")
	private String settlementPointName;	
	
	@Column(name="settlementPointType", length=15)
	@XmlElement(name="settlementPointType")
	private String settlementPointType;
	
	@Column(name="settlementPointPrice", precision=7, scale=2)
	@XmlElement(name="settlementPointPrice")
	private float settlementPointPrice;	
	
	@Column(name="velocity", precision=7, scale=2)
	@XmlElement(name="velocity")
	private float velocity = 0f;
	
	@Column(name="acceleration", precision=7, scale=2)
	@XmlElement(name="acceleration")
	private float acceleration = 0f;
	
	@Column(name="temperature", length=3)
	@XmlElement(name="temperature")
	private int temperature = 0;
	
	@Column(name="DSTFlag", length=3)
	@XmlElement(name="DSTFlag")
	private String DSTFlag;
	
	public Date getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Date value) {
	    intervalDate = value;
	}
	
	public void setSettlementPointName(String value) {
		settlementPointName = value;	
	}
	
	public String getSettlementPointName() {
		return settlementPointName;
	}
	
	public void setSettlementPointType(String value) {
		settlementPointType = value;	
	}
	
	public String getSettlementPointType() {
		return settlementPointType;
	}
	
	public void setSettlementPointPrice(float value) {
		settlementPointPrice = value;	
	}
	
	public float getSettlementPointPrice() {
		return settlementPointPrice;
	}
	
	public void setVelocity(float value) {
	    velocity = value;	
	}
	
	public float getVelocity() {
		return velocity;
	}
	
	public void setAcceleration(float value) {
		acceleration = value;	
	}
	
	public float getAcceleration() {
		return acceleration;
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public void setTemperature(final int value) {
		temperature = value;	
	}
	
	public void setDSTFlag(String value) {
		DSTFlag = value;	
	}
	
	public String getDSTFlag() {
		return DSTFlag;
	}
	
	@Transient
	private String deliveryDate;
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(final String value) {
		this.deliveryDate = value;
	}
	
	@Transient
	private Integer deliveryHour;
	public Integer getDeliveryHour() {
		return deliveryHour;
	}
	public void setDeliveryHour(final Integer value) {
		this.deliveryHour = value;
	}	
	
	@Transient
	private Integer deliveryInterval;
	public Integer getDeliveryInterval() {
		return deliveryInterval;
	}
	public void setDeliveryInterval(final Integer value) {
		this.deliveryInterval = value;
	}
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
