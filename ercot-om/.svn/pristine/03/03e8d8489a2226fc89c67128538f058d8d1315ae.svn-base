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
@Table(name="rtlmppccrn1h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmppccrn1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmppccrn1hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmppccrn1hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPPCCRN1H")
@Path("/RTLMPPCCRN1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPPCCRN1H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
