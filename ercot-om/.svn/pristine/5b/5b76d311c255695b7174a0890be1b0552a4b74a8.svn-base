package com.softwarelikeyou.model.entity.rtspp.lzew;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspplzew1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzew1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzew1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzew1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZEW1H")
@Path("/RTSPPLZEW1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZEW1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
