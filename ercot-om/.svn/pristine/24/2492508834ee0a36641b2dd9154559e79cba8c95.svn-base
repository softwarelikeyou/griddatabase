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
@Table(name="rtlmplzdc30m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmplzdc30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmplzdc30mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmplzdc30mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPLZDC30M")
@Path("/RTLMPLZDC30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPLZDC30M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
