package com.softwarelikeyou.model.entity.rtlmp.sh;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmpsh24h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmpsh24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmpsh24hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmpsh24hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPSH24H")
@Path("/RTLMPSH24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPSH24H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
