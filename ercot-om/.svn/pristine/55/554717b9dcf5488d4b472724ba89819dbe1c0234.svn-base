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
@Table(name="rtlmpah5m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmpah5m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmpah5mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmpah5mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPAH5M")
@Path("/RTLMPAH5M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPAH5M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
