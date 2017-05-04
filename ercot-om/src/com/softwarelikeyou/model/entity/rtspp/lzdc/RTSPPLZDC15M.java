package com.softwarelikeyou.model.entity.rtspp.lzdc;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspplzdc15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzdc15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzdc15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzdc15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZDC15M")
@Path("/RTSPPLZDC15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZDC15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
