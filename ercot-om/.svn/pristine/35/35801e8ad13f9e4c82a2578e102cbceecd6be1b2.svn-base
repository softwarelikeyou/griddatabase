package com.softwarelikeyou.model.entity.rtspp.lccrn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspplccrn30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplccrn30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplccrn30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplccrn30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLCCRN30M")
@Path("/RTSPPLCCRN30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLCCRN30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
