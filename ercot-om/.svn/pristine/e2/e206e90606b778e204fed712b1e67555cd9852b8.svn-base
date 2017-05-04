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
@Table(name="rtsppah1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtsppah1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtsppah1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtsppah1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPAH1H")
@Path("/RTSPPAH1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPAH1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
