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
@Table(name="rtspprn1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspprn1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspprn1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspprn1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPRN1H")
@Path("/RTSPPRN1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPRN1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
