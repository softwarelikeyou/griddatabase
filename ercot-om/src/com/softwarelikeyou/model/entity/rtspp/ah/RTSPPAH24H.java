package com.softwarelikeyou.model.entity.rtspp.ah;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtsppah24h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtsppah24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtsppah24hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtsppah24hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPHU24H")
@Path("/RTSPPHU24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPAH24H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
