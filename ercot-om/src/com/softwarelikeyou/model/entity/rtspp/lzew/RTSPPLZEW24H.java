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
@Table(name="rtspplzew24h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzew24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzew24hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzew24hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZEW24H")
@Path("/RTSPPLZEW24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZEW24H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
