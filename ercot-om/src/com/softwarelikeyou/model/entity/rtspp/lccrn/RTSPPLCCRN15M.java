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
@Table(name="rtspplccrn15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplccrn15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplccrn15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplccrn15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLCCRN15M")
@Path("/RTSPPLCCRN15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLCCRN15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
