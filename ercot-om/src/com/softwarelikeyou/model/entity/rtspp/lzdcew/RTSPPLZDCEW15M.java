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
@Table(name="rtspplzdcew15m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzdcew15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzdcew15mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzdcew15mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZDCEW15M")
@Path("/RTSPPLZDCEW15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZDCEW15M extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
