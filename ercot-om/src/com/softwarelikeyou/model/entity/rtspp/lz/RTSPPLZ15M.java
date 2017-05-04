package com.softwarelikeyou.model.entity.rtspp.lz;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspplz15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplz15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplz15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplz15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZ15M")
@Path("/RTSPPLZ15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZ15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
