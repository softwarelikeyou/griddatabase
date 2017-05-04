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
@Table(name="rtsppah30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtsppah30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtsppah30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtsppah30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPAH30M")
@Path("/RTSPPAH30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPAH30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
