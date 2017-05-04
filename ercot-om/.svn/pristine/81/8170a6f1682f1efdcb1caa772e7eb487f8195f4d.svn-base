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
@Table(name="rtspplz1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplz1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplz1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplz1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZ1H")
@Path("/RTSPPLZ1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZ1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
