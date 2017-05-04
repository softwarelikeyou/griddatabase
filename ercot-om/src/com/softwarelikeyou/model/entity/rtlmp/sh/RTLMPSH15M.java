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
@Table(name="rtlmpsh15m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmpsh15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmpsh15mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmpsh15mSettlementPointIndx", columnNames = {"settlementPoint"})
})
@XmlRootElement(name="RTLMPSH15M")
@Path("/RTLMPSH15M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTLMPSH15M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
