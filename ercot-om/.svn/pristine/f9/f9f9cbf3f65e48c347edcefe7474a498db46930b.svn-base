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
@Table(name="rtspplzdcew24h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtspplzdcew24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtspplzdcew24hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtspplzdcew24hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPLZDCEW24H")
@Path("/RTSPPLZDCEW24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPLZDCEW24H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
