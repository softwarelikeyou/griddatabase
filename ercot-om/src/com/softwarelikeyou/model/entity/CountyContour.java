package com.softwarelikeyou.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="countyContour")
@XmlRootElement(name="countyConour")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountyContour implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	public CountyContour() { }
	
	@Column(name="name", nullable=false)
	@XmlElement(name="name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(final String value) {
		this.name = value;
	}
	
	@Id
	@Column(name="namePart", nullable=false)
	@XmlElement(name="namePart")
	private String namePart;
	public String getNamePart() {
		return namePart;
	}
	public void setNamePart(final String value) {
		this.namePart = value;
	}
	
	@Id
	@Column(name="id")
	@XmlElement(name="id")
	private int id;
	public int getId() {
		return id;
	}
	public void setId(final int value) {
		this.id = value;
	}
	
	@Column(name="coordinate", nullable=false)
	@XmlElement(name="coordinate")
	private String coordinate;
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(final String value) {
		this.coordinate = value;
	}
}