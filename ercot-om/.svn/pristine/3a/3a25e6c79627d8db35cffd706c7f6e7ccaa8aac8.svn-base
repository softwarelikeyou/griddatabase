package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@MappedSuperclass
public abstract class Daily implements Serializable {
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@XmlEnum
	public enum Type {
		@XmlEnumValue(value = "All")
		All, 
		@XmlEnumValue(value = "Peak")
		Peak,
		@XmlEnumValue(value = "OffPeak")
		OffPeak;
	}
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name="type", nullable=false, length=10)
    @XmlElement(name="type")
	private Type type;
	public Type getType() {
		return type;
	}
	public void setType(final Type value) {
		type = value;
	}
	
	@Id
	@Column(name="name", length=25, nullable=false)
	@XmlElement(name="name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(final String value) {
		this.name = value;
	}
	
	@Id
	@Column(name="intervalDate", nullable=false)
	@XmlElement(name="intervalDate")
	private Date intervalDate;
	public Date getIntervalDate() {
		return intervalDate;
	}
	public void setIntervalDate(final Date value) {
		intervalDate = value;
	}
	
	@Column(name="low")
	@XmlElement(name="low")
	private float low = 9999.99f;
	public float getLow() {
		return low;
	}
	public void setLow(final float value) {
		low = value;
	}
	
	@Column(name="average")
	@XmlElement(name="average")
	private float average = 0f;
	public float getAverage() {
		return average;
	}
	public void setAverage(final float value) {
		average = value;
	}
	
	@Column(name="high")
	@XmlElement(name="high")
	private float high = 0f;
	public float getHigh() {
		return high;
	}
	public void setHigh(final float value) {
		high = value;
	}
	
	@Column(name="averageChange")
	@XmlElement(name="averageChange")
	private float averageChange = 0f;
	public float getAverageChange() {
		return averageChange;
	}
	public void setAverageChange(final float value) {
		averageChange = value;
	}	
}
