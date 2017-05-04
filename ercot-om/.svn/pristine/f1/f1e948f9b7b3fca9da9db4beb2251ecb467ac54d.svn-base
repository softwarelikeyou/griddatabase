package com.softwarelikeyou.model.entity.rtlmp;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.softwarelikeyou.exception.RTLMPException;
import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.util.Util;

@MappedSuperclass
@XmlRootElement(name="RTLMP")
@Path("/RTLMP")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMP implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name="RTDTimestamp", nullable=false)
	@XmlElement(name="RTDTimestamp")
	private Date RTDTimestamp = new Date();
	
	@Column(name="repeatedHourFlag", length=5)
	@XmlElement(name="repeatedHourFlag")
	private String repeatedHourFlag;	
	
	@Id
	@Column(name="intervalId", nullable=false, length=3)
	@XmlElement(name="intervalId")
	private int intervalId;
	
	@Id
	@Column(name="intervalEnding")
	@XmlElement(name="intervalEnding")
	private Date intervalEnding = new Date();
	
	@Column(name="intervalRepeatedHourFlag", length=5)
	@XmlElement(name="intervalRepeatedHourFlag")
	private String intervalRepeatedHourFlag;
	
	@Id
	@Column(name="settlementPoint", nullable=false, length=25)
	@XmlElement(name="settlementPoint")
	private String settlementPoint;
	
	@Column(name="settlementPointType", length=15)
	@XmlElement(name="settlementPointType")
	private String settlementPointType;
	
	@Column(name="LMP", precision=7, scale=2)
	@XmlElement(name="LMP")
	private float LMP = 0f;
	
	@Column(name="velocity", precision=7, scale=2)
	@XmlElement(name="velocity")
	private float velocity = 0f;	
	
	@Column(name="acceleration", precision=7, scale=2)
	@XmlElement(name="acceleration")
	private float acceleration = 0f;		
	
	@Column(name="temperature", length=3)
	@XmlElement(name="temperature")
	private int temperature = 0;
	
	public Date getRTDTimestamp() {
		return RTDTimestamp;
	}

	public void setRTDTimestamp(final Date value) {
		RTDTimestamp = value;
	}
	
	public String getRepeatedHourFlag() {
		return repeatedHourFlag;
	}
	
	public void setRepeatedHourFlag(final String value) {
		repeatedHourFlag = value;	
	}
	
	public int getIntervalId() {
		return intervalId;
	}
	
	public void setIntervalId(final int value) {
		intervalId = value;	
	}
	
	public Date getIntervalEnding() {
		return intervalEnding;
	} 
	
	public void setIntervalEnding(final Date value) {
		intervalEnding = value;	
	}
	
	public String getIntervalRepeatedHourFlag() {
		return intervalRepeatedHourFlag;
	}
	
	public void setIntervalRepeatedHourFlag(final String value) {
		intervalRepeatedHourFlag = value;	
	}
	
	public String getSettlementPoint() {
		return settlementPoint;
	}
	
	public void setSettlementPoint(final String value) {
		settlementPoint = value;	
	}
	
	public String getSettlementPointType() {
		return settlementPointType;
	}
	
	public void setSettlementPointType(final String value) {
		settlementPointType = value;	
	}
	
	public float getLMP() {
		return LMP;
	}
	
	public void setLMP(final float value) {
		LMP = value;	
	}

	public float getVelocity() {
		return velocity;
	}
	
	public void setVelocity(final float value) {
		velocity = value;	
	}
	
	public float getAcceleration() {
		return acceleration;
	}
	
	public void setAcceleration(final float value) {
		acceleration = value;	
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public void setTemperature(final int value) {
		temperature = value;	
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@GET
	@Path("/findByBetweenIntervalEndingDatesSettlementPoint")
	@Produces("application/xml")
	public List<RTLMP> findByBetweenIntervalEndingDatesSettlementPoint(@QueryParam("settlementPoint") String settlementPoint,
			                                                           @QueryParam("settlementPointType") String settlementPointType,
			                                                           @QueryParam("timeInterval") String timeInterval,
			                                                           @QueryParam("start") String start,
			                                                           @QueryParam("end") String end) {
		System.out.println("Hello World!!!");
		System.out.println(settlementPointType);
		System.out.println(timeInterval);
		System.out.println(start);
		System.out.println(end);
		
		if (settlementPoint == null     ||
			settlementPointType == null ||
		    timeInterval == null        ||
	        start == null               ||
	        end == null)
		        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
	    
		if (!RTLMPHelper.validateDate(start))
			throw new WebApplicationException(Response.Status.CONFLICT);
	
		if (!RTLMPHelper.validateDate(end))
			throw new WebApplicationException(Response.Status.CONFLICT);

		String key = settlementPointType + timeInterval;
		if (!RTLMPHelper.validateClass(key))
			throw new WebApplicationException(Response.Status.CONFLICT);
		
		try {
		    Date startDate = Util.stringToDate(start, "yyyyMMdd kk:mm:ss");
		    Date endDate = Util.stringToDate(end, "yyyyMMdd kk:mm:ss");
		    return RTLMPFacade.findByBetweenIntervalEndingDatesSettlementPoint(RTLMPHelper.getClass(key), startDate, endDate, settlementPoint);
		}
		catch (ParseException e) {
			throw new WebApplicationException(e.getCause());
		}
		catch (RTLMPException e) {
		    throw new WebApplicationException(e.getCause());
		}
	}
}
