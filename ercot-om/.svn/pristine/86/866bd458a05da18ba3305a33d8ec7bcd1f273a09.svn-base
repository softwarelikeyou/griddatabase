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

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.util.Util;

@Entity
@Table(name="h48damascs")
@org.hibernate.annotations.Table(appliesTo="h48damascs", 
		indexes=@org.hibernate.annotations.Index(name="intervalDateIdx", columnNames={"intervalDate"}))
@XmlRootElement(name="H48DAMASCS")
@Path("/H48DAMASCS")
@XmlAccessorType(XmlAccessType.FIELD)
public class H48DAMASCS implements Serializable {
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
	@Column(name="intervalDate")
	@XmlElement(name="intervalDate")
	private Date intervalDate = new Date();
	
	@Column(name="cleardNspin", precision=7, scale=2)
	@XmlElement(name="cleardNspin")
	private float clearedNspin = 0f;
	
	@Column(name="cleardRegdn", precision=7, scale=2)
	@XmlElement(name="cleardRegdn")
	private float clearedRegdn = 0f;
	
	@Column(name="cleardRegup", precision=7, scale=2)
	@XmlElement(name="cleardRegup")
	private float clearedRegup  = 0f;
	
	@Column(name="cleardRrsgen", precision=7, scale=2)
	@XmlElement(name="cleardRrsgen")
	private float clearedRrsgen  = 0f;
	
	@Column(name="cleardRrsload", precision=7, scale=2)
	@XmlElement(name="cleardRrsload")
	private float clearedRrsload   = 0f;
	
	@Column(name="selfArrangedNspin", precision=7, scale=2)
	@XmlElement(name="selfArrangedNspin")
	private float selfArrangedNspin = 0f;
	
	@Column(name="selfArrangedRegdn", precision=7, scale=2)
	@XmlElement(name="selfArrangedRegdn")
	private float selfArrangedRegdn = 0f;
	
	@Column(name="selfArrangedRegup", precision=7, scale=2)
	@XmlElement(name="selfArrangedRegup")
	private float selfArrangedRegup  = 0f;
	
	@Column(name="selfArrangedRrsgen", precision=7, scale=2)
	@XmlElement(name="selfArrangedRrsgen")
	private float selfArrangedRrsgen  = 0f;
	
	@Column(name="selfArrangedRrsload", precision=7, scale=2)
	@XmlElement(name="selfArrangedRrsload")
	private float selfArrangedRrsload   = 0f;
	
	public Date getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Date intervalDate) {
		this.intervalDate = intervalDate;
	}
	
	public float getClearedNspin() {
		return clearedNspin;
	}

	public void setClearedNspin(float value) {
		this.clearedNspin = value;
	}
	
	public float getClearedRegdn() {
		return clearedRegdn;
	}

	public void setClearedRegdn(float value) {
		this.clearedRegdn = value;
	}
	
	public float getClearedRegup() {
		return clearedRegup;
	}

	public void setcClearedRegup(float value) {
		this.clearedRegup = value;
	}
	
	public float getClearedRrsgen() {
		return clearedRrsgen;
	}

	public void setClearedRssgen(float value) {
		this.clearedRrsgen = value;
	}
	
	public float getClearedRrsloadPrice() {
		return clearedRrsload;
	}

	public void setClearedRrsload(float value) {
		this.clearedRrsload = value;
	}
	
	public float getSelfArrangedNspin() {
		return selfArrangedNspin;
	}

	public void setSelfArrangedNspin(float value) {
		this.selfArrangedNspin = value;
	}
	
	public float getSelfArrangedRegdn() {
		return selfArrangedRegdn;
	}

	public void setSelfArrangedRegdn(float value) {
		this.selfArrangedRegdn = value;
	}
	
	public float getSelfArrangedRegup() {
		return selfArrangedRegup;
	}

	public void setcSelfArrangedRegup(float value) {
		this.selfArrangedRegup = value;
	}
	
	public float getSelfArrangedRrsgen() {
		return selfArrangedRrsgen;
	}

	public void setSelfArrangedRssgen(float value) {
		this.selfArrangedRrsgen = value;
	}
	
	public float getSelfArrangedRrsloadPrice() {
		return selfArrangedRrsload;
	}

	public void setSelfArrangedRrsload(float value) {
		this.selfArrangedRrsload = value;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@GET
	@Path("/findByDate")
	@Produces("application/xml")
	public List<H48DAMASCS> findByDate(@QueryParam("date") String date) {
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
			
		final List<H48DAMASCS> list = null;
		try {
			//list = H48DAMASFacade.findByBetweenDates(start.getTime(), end.getTime());
		}
		catch (Exception e) {
			throw new WebApplicationException(e.getCause());
		}
		return list;
	}
}
