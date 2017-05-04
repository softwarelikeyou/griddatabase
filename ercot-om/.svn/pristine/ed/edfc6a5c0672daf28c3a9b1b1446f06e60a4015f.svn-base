package com.softwarelikeyou.model.entity.rtlmp.lz;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmplz1h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmplz1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmplz1hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmplz1hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPLZ1H")
@Path("/RTLMPLZ1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPLZ1H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
