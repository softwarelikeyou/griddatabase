package com.softwarelikeyou.model.entity.rtlmp.hu;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmphu1h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmphu1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmphu1hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmphu1hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPHU1H")
@Path("/RTLMPHU1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPHU1H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
