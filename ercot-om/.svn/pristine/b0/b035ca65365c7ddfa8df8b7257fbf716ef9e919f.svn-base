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
@Table(name="rtspppun1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspppun1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspppun1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspppun1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPPUN1H")
@Path("/RTSPPPUN1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPPUN1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
