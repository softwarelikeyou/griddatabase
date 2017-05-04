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
@Table(name="rtspplccrn24h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplccrn24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplccrn24hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplccrn24hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLCCRN24H")
@Path("/RTSPPLCCRN24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLCCRN24H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}