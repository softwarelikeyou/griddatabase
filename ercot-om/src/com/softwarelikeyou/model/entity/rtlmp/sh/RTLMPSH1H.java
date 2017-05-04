package com.softwarelikeyou.model.entity.rtlmp.sh;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmpsh1h", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmpsh1h", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmpsh1hRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmpsh1hSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPSH1H")
@Path("/RTLMPSH1H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPSH1H extends RTLMP {
	private static final long serialVersionUID = 1L;

}
