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
@Table(name="rtspprn30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspprn30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspprn30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspprn30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPRN30M")
@Path("/RTSPPRN30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPRN30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
