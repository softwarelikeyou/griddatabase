package com.softwarelikeyou.model.entity.ascpc;

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

import com.softwarelikeyou.exception.ASCPCException;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.util.Util;

@Entity
@Table(name="ascpc")
@org.hibernate.annotations.Table(appliesTo="ascpc", 
		indexes=@org.hibernate.annotations.Index(name="intervalDateIdx", columnNames={"intervalDate"}))
@Path("/ASCPC")
@XmlRootElement(name="AS")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASCPC implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="intervalDate")
	@XmlElement(name="intervalDate")
	private Date intervalDate = new Date();	
	
	@Column(name="NSPIN", precision=7, scale=2)
	@XmlElement(name="NSPIN")
	private float NSPIN = 0f;
	
	@Column(name="NSPINVelocity", precision=7, scale=2)
	@XmlElement(name="NSPINVelocity")
	private float NSPINVelocity = 0f;
	
	@Column(name="NSPINAcceleration", precision=7, scale=2)
	@XmlElement(name="NSPINAcceleration")
	private float NSPINAcceleration = 0f;
	
	@Column(name="REGDN", precision=7, scale=2)
	@XmlElement(name="REGDN")
	private float REGDN = 0f;
	 
	@Column(name="REGDNDVelocty", precision=7, scale=2)
	@XmlElement(name="REGDNVelocity")
	private float REGDNVelocity = 0f;
	
	@Column(name="REGDNDAcceleration", precision=7, scale=2)
	@XmlElement(name="REGDNAcceleration")
	private float REGDNAcceleration = 0f;
	
	@Column(name="REGUP", precision=7, scale=2)
	@XmlElement(name="REGUP")
	private float REGUP = 0f;
	 
	@Column(name="REGUPVelocity", precision=7, scale=2)
	@XmlElement(name="REGUPVelocity")
	private float REGUPVelocity = 0f;
	
	@Column(name="REGUPAcceleration", precision=7, scale=2)
	@XmlElement(name="REGUPAcceleration")
	private float REGUPAcceleration = 0f;
	
	@Column(name="RRS", precision=7, scale=2)
	@XmlElement(name="RRS")
	private float RRS = 0f;
	
	@Column(name="RRSVelocity", precision=7, scale=2)
	@XmlElement(name="RRSVelocity")
	private float RRSVelocity = 0f;
	
	@Column(name="RRSAcceleration", precision=7, scale=2)
	@XmlElement(name="RRSAcceleration")
	private float RRSAcceleration = 0f;
	
	public Date getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Date intervalDate) {
		this.intervalDate = intervalDate;
	}

	public float getNSPIN() {
		return NSPIN;
	}

	public void setNSPIN(float value) {
		NSPIN = value;
	}

	public float getNSPINVelocity() {
		return NSPINVelocity;
	}

	public void setNSPINVelocity(float value) {
		NSPINVelocity = value;
	}
	
	public float getNSPINAcceleration() {
		return NSPINAcceleration;
	}

	public void setNSPINAcceleration(float value) {
		NSPINAcceleration = value;
	}
	
	public float getREGDN() {
		return REGDN;
	}

	public void setREGDN(float value) {
		REGDN = value;
	}

	public float getREGDNVelocity() {
		return REGDNVelocity;
	}

	public void setREGDNVelocity(float value) {
		REGDNVelocity = value;
	}
	
	public float getREGDNAcceleration() {
		return REGDNAcceleration;
	}

	public void setREGDNAcceleration(float value) {
		REGDNAcceleration = value;
	}
	
	public float getREGUP() {
		return REGUP;
	}

	public void setREGUP(float value) {
		REGUP = value;
	}

	public float getREGUPVelocity() {
		return REGUPVelocity;
	}

	public void setREGUPVelocity(float value) {
		REGUPVelocity = value;
	}
	
	public float getREGUPAcceleration() {
		return REGUPAcceleration;
	}

	public void setREGUPAcceleration(float value) {
		REGUPAcceleration = value;
	}
	
	public float getRRS() {
		return RRS;
	}

	public void setRRS(float value) {
		RRS = value;
	}
		
	public float getRRSVelocity() {
		return RRSVelocity;
	}

	public void setRRSVelocity(float value) {
		RRSVelocity = value;
	}
	
	public float getRRSAcceleration() {
		return RRSAcceleration;
	}

	public void setRRSAcceleration(float value) {
		RRSAcceleration = value;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Transient
	private String ancillaryType;
	public String getAncillaryType() {
		return ancillaryType;
	}
	public void setAncillaryType(final String value) {
		this.ancillaryType = value;
	}
	
	@Transient
	private float MCPC;
	public float getMCPC() {
		return MCPC;
	}
	public void setMCPC(final float value) {
		this.MCPC = value;
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
	private String hourEnding;
	public String getHourEnding() {
		return hourEnding;
	}
	public void setHourEnding(final String value) {
		this.hourEnding = value;
	}
	
	@GET
	@Path("/findByDate")
	@Produces("application/xml")
	public List<ASCPC> findByDate(@QueryParam("date") String date) {
		if (date == null)
			throw new WebApplicationException(new ASCPCException("Date cannot be empty"));
		
		Date seed = null;
		try {
		    seed = Util.stringToDate(date, "yyyy-MM-dd");
		}
		catch (ParseException e) {
			throw new WebApplicationException(new ASCPCException("Date format is invalid, must be yyyy-MM-dd"));
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
			
		final List<ASCPC> list;
		try {
			list = ASCPCFacade.findBetweenDates(start.getTime(), end.getTime());
		}
		catch (ASCPCException e) {
			throw new WebApplicationException(e.getCause());
		}
		return list;
	}
}