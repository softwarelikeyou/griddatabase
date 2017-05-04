package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.exception.H48DAMHPException;
import com.softwarelikeyou.facade.H48DAMHPFacade;
import com.softwarelikeyou.util.Util;

@Entity
@Table(name="h48damhp")
@org.hibernate.annotations.Table(appliesTo="h48damhp", 
		indexes=@org.hibernate.annotations.Index(name="intervalDateIdx", columnNames={"intervalDate"}))
@XmlRootElement(name="H48DAMHP")
@Path("/H48DAMHP")
@XmlAccessorType(XmlAccessType.FIELD)
public class H48DAMHP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="intervalDate")
	@XmlElement(name="intervalDate")
	private Date intervalDate = new Date();
	
	@Id
	@Column(name="resourceName", length=25)
	@XmlElement(name="resourceName")
	private String resourceName;
	
	@Id
	@Column(name="asType", length=5)	
	@XmlElement(name="asType")
	private String asType;
	
	@Id
	@Column(name="blockIndicator", length=5)	
	@XmlElement(name="blockIndicator")
	private String blockIndicator;
	
	@Column(name="offeredQuantity", precision=7, scale=2)
	@XmlElement(name="offeredQuantity")
	private float offeredQuantity;
	
	@Column(name="offeredPrice", precision=7, scale=2)
	@XmlElement(name="offeredPrice")
	private float offeredPrice = 0f;
	
	public Date getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Date intervalDate) {
		this.intervalDate = intervalDate;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getAsType() {
		return asType;
	}

	public void setAsType(String asType) {
		this.asType = asType;
	}

	public String getBlockIndicator() {
		return blockIndicator;
	}

	public void setBlockIndicator(String blockIndicator) {
		this.blockIndicator = blockIndicator;
	}

	public float getOfferedQuantity() {
		return offeredQuantity;
	}

	public void setOfferedQuantity(float offeredQuantity) {
		this.offeredQuantity = offeredQuantity;
	}

	public float getOfferedPrice() {
		return offeredPrice;
	}

	public void setOfferedPrice(float offeredPrice) {
		this.offeredPrice = offeredPrice;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@GET
	@Path("/findByDate")
	@Produces("application/xml")
	public List<H48DAMHP> findByDate(@QueryParam("date") String date) {
		if (date == null)
			throw new WebApplicationException(new H48DAMHPException("Date cannot be empty"));
		
		Date seed = null;
		try {
		    seed = Util.stringToDate(date, "yyyy-MM-dd");
		}
		catch (ParseException e) {
			throw new WebApplicationException(new H48DAMHPException("Date format is invalid, must be yyyy-MM-dd"));
		}
			
		Calendar start = new GregorianCalendar();
		start.setTime(seed);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
			
		Calendar end = new GregorianCalendar();
		end.setTime(seed);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);	
			
		final List<H48DAMHP> list;
		try {
			list = H48DAMHPFacade.findByBetweenDates(start.getTime(), end.getTime());
		}
		catch (H48DAMHPException e) {
			throw new WebApplicationException(e.getCause());
		}
		return list;
	}
}
