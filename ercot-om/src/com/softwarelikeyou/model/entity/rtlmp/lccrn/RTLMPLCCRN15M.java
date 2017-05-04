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
@Table(name="rtlmplccrn15m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmplccrn15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmplccrn15mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmplccrn15mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPLCCRN15M")
@Path("/RTLMPLCCRN15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPLCCRN15M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
