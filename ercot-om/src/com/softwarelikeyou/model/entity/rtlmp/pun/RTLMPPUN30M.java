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
@Table(name="rtlmppun30m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmppun30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmppun30mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmppun30mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPPUN30M")
@Path("/RTLMPPUN30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPPUN30M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
