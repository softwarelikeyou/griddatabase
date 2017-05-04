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
@Table(name="rtspppccrn15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspppccrn15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspppccrn15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspppccrn15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPPCCRN15M")
@Path("/RTSPPPCCRN15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPPCCRN15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
