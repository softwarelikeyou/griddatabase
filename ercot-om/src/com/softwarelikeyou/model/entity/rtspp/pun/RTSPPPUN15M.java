package com.softwarelikeyou.model.entity.rtspp.pun;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspppun15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspppun15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspppun15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspppun15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPPUN15M")
@Path("/RTSPPPUN15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPPUN15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
