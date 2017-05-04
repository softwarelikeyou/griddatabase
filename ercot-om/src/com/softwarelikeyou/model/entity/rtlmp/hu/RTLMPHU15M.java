package com.softwarelikeyou.model.entity.rtlmp.hu;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

@Entity
@Table(name="rtlmphu15m", uniqueConstraints = @UniqueConstraint(columnNames = { "RTDTimestamp", "intervalEnding", "settlementPoint" }))
@org.hibernate.annotations.Table(appliesTo="rtlmphu15m", indexes = { 
@org.hibernate.annotations.Index(name = "rtlmphu15mRTDTimestampIndx", columnNames = {"RTDTimestamp"}),
@org.hibernate.annotations.Index(name = "rtlmphu15mSettlementPointIndx", columnNames = {"settlementPoint"})
})
public class RTLMPHU15M extends RTLMP {
	private static final long serialVersionUID = 1L;

}
