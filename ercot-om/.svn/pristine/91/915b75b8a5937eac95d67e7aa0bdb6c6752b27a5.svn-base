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
@Table(name="rtlmplccrn24h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmplccrn24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmplccrn24hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmplccrn24hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPLCCRN24H")
@Path("/RTLMPLCCRN24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPLCCRN24H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
