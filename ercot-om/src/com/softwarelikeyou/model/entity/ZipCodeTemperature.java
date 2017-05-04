package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="zipCodeTemperature", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "datetime" }))
@XmlRootElement(name="zipCodeTemperature")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZipCodeTemperature implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	public ZipCodeTemperature() { }
	
	@Id
	@Column(name="id")
	@GeneratedValue
	@XmlElement(name="id")
	private long id;
	
	public long getId() {
		return id;
	}
	
	public void setId(final long value) {
		id = value;
	}
	
	@Column(name="name", nullable=false)
	@XmlElement(name="name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(final String value) {
		this.name = value;
	}
	
	@Column(name="datetime", nullable=false)
	@XmlElement(name="datetime")
	private Date datetime = new Date();	
	
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(final Date value) {
		this.datetime = value;
	}
	
	@Column(name="temperature", columnDefinition = "int(3) default -50")
	@XmlElement(name="temperature")
	private Integer temperature = -50;	
	
	public Integer getTemperature() {
		return temperature;
	}
	
	public void setTemperature(final Integer value) {
		temperature = value;
	}
}