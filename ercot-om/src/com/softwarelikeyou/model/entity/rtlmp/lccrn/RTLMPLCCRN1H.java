package com.softwarelikeyou.model.entity.rtlmp.lccrn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmplccrn1h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmplccrn1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmplccrn1hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmplccrn1hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPLCCRN1H")
@Path("/RTLMPLCCRN1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPLCCRN1H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
