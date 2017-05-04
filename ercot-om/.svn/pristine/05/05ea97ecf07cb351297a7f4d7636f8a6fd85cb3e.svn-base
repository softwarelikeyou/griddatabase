package com.softwarelikeyou.model.entity.rtspp.sh;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

@Entity
@Table(name="rtsppsh30m", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtsppsh30m", indexes = { 
@org.hibernate.annotations.Index(name = "rtsppsh30mSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtsppsh30mIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPSH30M")
@Path("/RTSPPSH30M")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPSH30M extends RTSPP {
	private static final long serialVersionUID = 1L;

}
