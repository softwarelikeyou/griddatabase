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
@Table(name="rtlmprn30m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmprn30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmprn30mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmprn30mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPRN30M")
@Path("/RTLMPRN30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPRN30M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
