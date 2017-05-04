package com.softwarelikeyou.model.entity.rtspp.pccrn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspppccrn24h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspppccrn24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspppccrn24hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspppccrn24hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPPCCRN24H")
@Path("/RTSPPPCCRN24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPPCCRN24H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
