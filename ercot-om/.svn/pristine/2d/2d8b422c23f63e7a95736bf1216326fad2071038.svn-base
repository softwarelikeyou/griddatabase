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
@Table(name="rtlmpah15m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmpah15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmpah15mRTDTimestampIndx", columnNames = { "RTDTimestamp" }),
@org.hibernate.annotations.Index(name = "rtlmpah15mSettlementPointIndx", columnNames = { "settlementPoint" })
})
@XmlRootElement(name="RTLMPAH15M")
@Path("/RTLMPAH15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPAH15M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
