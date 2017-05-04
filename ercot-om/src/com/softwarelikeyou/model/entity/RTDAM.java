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

import com.softwarelikeyou.exception.RTDAMException;
import com.softwarelikeyou.facade.RTDAMFacade;
import com.softwarelikeyou.util.Util;

@Entity
@Table(name="rtdam")
@org.hibernate.annotations.Table(appliesTo="rtdam", 
		indexes=@org.hibernate.annotations.Index(name="intervalDatePointIdx", columnNames={ "intervalDate", "settlementPoint" }))
@XmlRootElement(name="RTDAM")
@Path("/RTDAM")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTDAM implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="intervalDate")
	@XmlElement(name="intervalDate")
	private Date intervalDate = new Date();
	
	@Id
	@Column(name="settlementPoint", length=25)
	@XmlElement(name="settlementPoint")
	private String settlementPoint;	
		
	@Column(name="settlementPointPrice", precision=7, scale=2)
	@XmlElement(name="settlementPointPrice")
	private float settlementPointPrice = 0f;	
	
	@Column(name="velocity", precision=7, scale=2)
	@XmlElement(name="velocity")
	private float velocity = 0f;	
	
	@Column(name="acceleration", precision=7, scale=2)
	@XmlElement(name="acceleration")
	private float acceleration = 0f;
	
	@Column(name="DSTFlag", length=3)
	@XmlElement(name="DSTFlg")
	private String DSTFlag;	
	
	public Date getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Date value) {
		intervalDate = value;
	}
	
	public void setSettlementPointName(String value) {
		settlementPoint = value;	
	}
	
	public String getSettlementPoint() {
		return settlementPoint;
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
	
	public void setDSTFlag(String value) {
		DSTFlag = value;	
	}
	
	public String getDSTFlag() {
		return DSTFlag;
	}
	
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
	
	@GET
	@Path("/findByDate")
	@Produces("application/xml")
	public List<RTDAM> findByDate(@QueryParam("date") String date) {
		if (date == null)
			throw new WebApplicationException(new RTDAMException("Date cannot be empty"));
		
		Date seed = null;
		try {
		    seed = Util.stringToDate(date, "yyyy-MM-dd");
		}
		catch (ParseException e) {
			throw new WebApplicationException(new RTDAMException("Date format is invalid, must be yyyy-MM-dd"));
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
			
		final List<RTDAM> list;
		try {
			list = RTDAMFacade.findByBetweenDates(start.getTime(), end.getTime());
		}
		catch (RTDAMException e) {
			throw new WebApplicationException(e.getCause());
		}
		return list;
	}
}