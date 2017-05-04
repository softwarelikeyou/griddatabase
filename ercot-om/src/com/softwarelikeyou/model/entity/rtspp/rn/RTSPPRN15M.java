package com.softwarelikeyou.model.entity.rtspp.rn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspprn15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspprn15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspprn15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspprn15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPRN15M")
@Path("/RTSPPRN15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPRN15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
