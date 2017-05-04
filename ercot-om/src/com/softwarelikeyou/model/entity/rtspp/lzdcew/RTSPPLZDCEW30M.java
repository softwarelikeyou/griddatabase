package com.softwarelikeyou.model.entity.rtspp.lzdcew;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtspplzdcew30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzdcew30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzdcew30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzdcew30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZDCEW30M")
@Path("/RTSPPLZDCEW30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZDCEW30M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
