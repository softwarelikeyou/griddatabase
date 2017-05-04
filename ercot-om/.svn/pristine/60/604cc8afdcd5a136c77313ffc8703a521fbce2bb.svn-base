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
@Table(name="rtspplzew30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzew30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzew30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzew30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZEW30M")
@Path("/RTSPPLZEW30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZEW30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
