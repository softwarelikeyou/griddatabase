package com.softwarelikeyou.model.entity.rtspp.lccrn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;


@Entity
@Table(name="rtspplccrn1h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplccrn1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplccrn1hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplccrn1hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLCCRN1H")
@Path("/RTSPPLCCRN1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLCCRN1H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
