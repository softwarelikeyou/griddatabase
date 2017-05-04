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
@Table(name="rtspppccrn1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspppccrn1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspppccrn1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspppccrn1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPPCCRN1H")
@Path("/RTSPPPCCRN1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPPCCRN1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
