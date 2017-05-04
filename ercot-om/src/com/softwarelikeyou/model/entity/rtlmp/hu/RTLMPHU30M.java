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
@Table(name="rtlmphu30m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmphu30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmphu30mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmphu30mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPHU30M")
@Path("/RTLMPHU30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPHU30M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
