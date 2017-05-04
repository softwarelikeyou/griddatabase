package com.softwarelikeyou.model.entity.rtspp.lzew;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspplzew15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzew15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzew15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzew15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZEW15M")
@Path("/RTSPPLEW15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZEW15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
