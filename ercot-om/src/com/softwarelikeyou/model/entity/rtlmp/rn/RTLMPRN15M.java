package com.softwarelikeyou.model.entity.rtlmp.rn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmprn15m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmprn15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmprn15mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmprn15mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPRN15M")
@Path("/RTLMPRN15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPRN15M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
