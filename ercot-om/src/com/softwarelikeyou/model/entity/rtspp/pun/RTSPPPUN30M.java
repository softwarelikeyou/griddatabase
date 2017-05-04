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
@Table(name="rtspppun30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspppun30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspppun30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspppun30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPPUN30M")
@Path("/RTSPPPUN30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPPUN30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
