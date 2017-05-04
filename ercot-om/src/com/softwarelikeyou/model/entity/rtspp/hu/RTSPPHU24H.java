package com.softwarelikeyou.model.entity.rtspp.hu;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspphu24h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspphu24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspphu24hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspphu24hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPHU24H")
@Path("/RTSPPHU24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPHU24H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
