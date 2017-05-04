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
@Table(name="rtsppah15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtsppah15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtsppah15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtsppah15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPAH15M")
@Path("/RTSPPAH15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPAH15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
