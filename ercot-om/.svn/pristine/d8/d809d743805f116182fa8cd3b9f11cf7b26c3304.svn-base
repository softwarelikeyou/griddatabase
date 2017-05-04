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
@Table(name="rtspppccrn30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspppccrn30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspppccrn30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspppccrn30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPPCCRN30M")
@Path("/RTSPPPCCRN30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPPCCRN30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
