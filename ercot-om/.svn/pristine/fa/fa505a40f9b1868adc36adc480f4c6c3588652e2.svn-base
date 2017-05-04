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
@Table(name="rtlmplccrn5m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmplccrn5m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmplccrn5mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmplccrn5mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPLCCRN5M")
@Path("/RTLMPLCCRN5M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPLCCRN5M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
