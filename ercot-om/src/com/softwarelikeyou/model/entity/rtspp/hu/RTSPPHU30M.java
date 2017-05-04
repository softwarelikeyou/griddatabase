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
@Table(name="rtspphu30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspphu30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspphu30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspphu30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPHU30M")
@Path("/RTSPPHU30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPHU30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
