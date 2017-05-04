package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.exception.SettlementPointException;
import com.softwarelikeyou.facade.SettlementPointFacade;

@Entity
@Table(name="settlementPoint")
@Path("/SettlementPoint")
@XmlRootElement(name="SettlementPoint")
@XmlAccessorType(XmlAccessType.FIELD)
public class SettlementPoint implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="name")
	@XmlElement(name="name")
	private String name;	
	
	@Column(name="plant")
	@XmlElement(name="plant")
	private String plant;	
	
	@Column(name="address")
	@XmlElement(name="address")
	private String address;	
	
	@Column(name="city")
	@XmlElement(name="city")
	private String city;	
	
	@Column(name="state")
	@XmlElement(name="state")
	private String state;
	
	@Column(name="zip")
	@XmlElement(name="zip")
	private String zip;	
	
	@Column(name="county")
	@XmlElement(name="county")
	private String county;	
	
	@Column(name="utility")
	@XmlElement(name="utility")
	private String utility;	
	
	@Column(name="longitude")
	@XmlElement(name="longitude")
	private String longitude;	
	
	@Column(name="latitude")
	@XmlElement(name="latitude")
	private String latitude;	
		
	@Column(name="temperature", columnDefinition = "int(3) default -50")
	@XmlElement(name="temperature")
	private Integer temperature = -50;	
	
	@Column(name="description")
	@Lob
	@XmlElement(name="description")
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getPlant() {
		return plant;
	}

	public void setPlant(String value) {
		this.plant = value;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String value) {
		this.city = value;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String value) {
		this.state = value;
	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String value) {
		this.zip = value;
	}
	
	public String getCounty() {
		return county;
	}

	public void setCounty(String value) {
		this.county = value;
	}
	
	public String getUtility() {
		return utility;
	}

	public void setUtility(String value) {
		this.utility = value;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String value) {
		this.longitude = value;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String value) {
		this.latitude = value;;
	}
	
	public Integer getTemperature() {
		return temperature;
	}
	
	public void setTemperature(final Integer value) {
		temperature = value;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(final String value) {
		this.description = value;
	}
	
	public enum Type {
		LZ,
		HU;
	}
	
	@GET
	@Path("/findAll")
	@Produces("application/xml")
	public List<SettlementPoint> findAll() {
		final List<SettlementPoint> list;
		try {
			list = SettlementPointFacade.findAll();
		}
		catch (SettlementPointException e) {
			throw new WebApplicationException(e.getCause());
		}
		return list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SettlementPoint other = (SettlementPoint) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}