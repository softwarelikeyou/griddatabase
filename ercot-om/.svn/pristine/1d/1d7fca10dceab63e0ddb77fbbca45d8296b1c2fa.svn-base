package com.softwarelikeyou.model.entity.rtlmp.lzdc;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmplzdc1h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmplzdc1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmplzdc1hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmplzdc1hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPLZDC1H")
@Path("/RTLMPLZDC1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPLZDC1H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
