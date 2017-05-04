package com.softwarelikeyou.model.entity.rtspp.sh;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.util.Util;

@Entity
@Table(name="rtsppsh15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtsppsh15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtsppsh15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtsppsh15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPSH15M")
@Path("/RTSPPSH15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPSH15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
	@GET
	@Path("/findByDate")
	@Produces("application/xml")
	public List<RTSPP> findByDate(@QueryParam("date") String date) {
		if (date == null)
			throw new WebApplicationException(new RTSPPException("Date cannot be empty"));
		
		Date seed = null;
		try {
		    seed = Util.stringToDate(date, "yyyy-MM-dd");
		}
		catch (ParseException e) {
			throw new WebApplicationException(new RTSPPException("Date format is invalid, must be yyyy-MM-dd"));
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
			
		final List<RTSPP> list;
		try {
			list = (List<RTSPP>) RTSPPFacade.findByIntervalDates(RTSPPSH15M.class, start.getTime(), end.getTime());
		}
		catch (RTSPPException e) {
			throw new WebApplicationException(e.getCause());
		}
		return list;
	}
}
