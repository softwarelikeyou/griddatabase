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
@Table(name="rtspplzdc30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzdc30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzdc30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzdc30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZDC30M")
@Path("/RTSPPLZDC30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZDC30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
