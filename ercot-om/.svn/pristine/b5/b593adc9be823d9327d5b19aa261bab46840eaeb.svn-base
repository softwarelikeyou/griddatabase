package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.util.Util;

@Entity
@Table(name="h48damasagregup")
@org.hibernate.annotations.Table(appliesTo="h48damasagregup", 
		indexes=@org.hibernate.annotations.Index(name="intervalDateIdx", columnNames={"intervalDate"}))
@XmlRootElement(name="H48DAMASAGREGUP")
@Path("/H48DAMASAGREGUP")
@XmlAccessorType(XmlAccessType.FIELD)
public class H48DAMASAGREGUP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String hourEnding;	
	public String getHourEnding() {
		return hourEnding;
	}
	public void setHourEnding(final String value) {
		hourEnding = value;
	}
	
	@Transient
	private String deliveryDate;
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(final String value) {
		deliveryDate = value;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue
	@XmlElement(name="id")
	private long id;
	
	@Column(name="intervalDate")
	@XmlElement(name="intervalDate")
	private Date intervalDate = new Date();
	
	@Column(name="offered", precision=7, scale=2)
	@XmlElement(name="offered")
	private float offered;
	
	@Column(name="price", precision=7, scale=2)
	@XmlElement(name="price")
	private float price = 0f;
	
	public long getId() {
		return id;
	}
	
	public void setId(final long value) {
		id = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Date intervalDate) {
		this.intervalDate = intervalDate;
	}
	
	public float getOffered() {
		return offered;
	}

	public void setOffered(float value) {
		this.offered = value;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float value) {
		this.price = value;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@GET
	@Path("/findByDate")
	@Produces("application/xml")
	public List<H48DAMASAGREGUP> findByDate(@QueryParam("date") String date) {
		if (date == null)
			throw new WebApplicationException(new H48DAMASException("Date cannot be empty"));
		
		Date seed = null;
		try {
		    seed = Util.stringToDate(date, "yyyy-MM-dd");
		}
		catch (ParseException e) {
			throw new WebApplicationException(new H48DAMASException("Date format is invalid, must be yyyy-MM-dd"));
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
			
		final List<H48DAMASAGREGUP> list = null;
		try {
			//list = H48DAMASFacade.findByBetweenDates(start.getTime(), end.getTime());
		}
		catch (Exception e) {
			throw new WebApplicationException(e.getCause());
		}
		return list;
	}
}
