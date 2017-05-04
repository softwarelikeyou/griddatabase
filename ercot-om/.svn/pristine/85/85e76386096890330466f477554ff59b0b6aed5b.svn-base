package com.softwarelikeyou.model.entity.rtlmp.pccrn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmppccrn24h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmppccrn24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmppccrn24hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmppccrn24hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPPCCRN24H")
@Path("/RTLMPPCCRN24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPPCCRN24H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
