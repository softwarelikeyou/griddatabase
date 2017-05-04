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
@Table(name="rtspplzdc1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzdc1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzdc1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzdc1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZDC1H")
@Path("/RTSPPLZDC1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZDC1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
