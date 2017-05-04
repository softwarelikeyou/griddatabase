package com.softwarelikeyou.model.entity.rtlmp.ah;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmpah30m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmpah30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmpah30mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmpah30mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPAH30M")
@Path("/RTLMPAH30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPAH30M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
