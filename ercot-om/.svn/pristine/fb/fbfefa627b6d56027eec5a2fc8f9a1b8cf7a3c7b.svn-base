package com.softwarelikeyou.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="zipCode", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@XmlRootElement(name="zipCode")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZipCode implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	public ZipCode() { }
	
	@Id
	@Column(name="name", unique=true, nullable=false)
	@XmlElement(name="name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(final String value) {
		this.name = value;
	}
	
	@Column(name="county", nullable=false)
	@XmlElement(name="county")
	private String county;
	public String getCounty() {
		return county;
	}
	public void setCounty(final String value) {
		this.county = value;
	}
}