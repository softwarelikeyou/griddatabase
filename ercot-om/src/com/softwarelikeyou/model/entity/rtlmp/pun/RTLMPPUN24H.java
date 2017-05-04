package com.softwarelikeyou.model.entity.rtlmp.pun;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmppun24h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmppun24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmppun24hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmppun24hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPPUN24H")
@Path("/RTLMPPUN24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPPUN24H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
