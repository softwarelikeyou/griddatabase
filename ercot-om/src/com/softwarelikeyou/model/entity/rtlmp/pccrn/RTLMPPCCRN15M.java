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
@Table(name="rtlmppccrn15m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmppccrn15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmppccrn15mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmppccrn15mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPPCCRN15M")
@Path("/RTLMPPCCRN15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPPCCRN15M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
