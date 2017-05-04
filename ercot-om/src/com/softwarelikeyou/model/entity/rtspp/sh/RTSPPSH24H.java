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
@Table(name="rtsppsh24h", uniqueConstraints = @UniqueConstraint(columnNames = { "intervalDate", "settlementPointName" }))
@org.hibernate.annotations.Table(appliesTo="rtsppsh24h", indexes = { 
@org.hibernate.annotations.Index(name = "rtsppsh24hSettlementPointIndx", columnNames = {"settlementPointName"}),
@org.hibernate.annotations.Index(name = "rtsppsh24hIntervalDateIndx", columnNames = {"intervalDate"})
})
@XmlRootElement(name="RTSPPSH24H")
@Path("/RTSPPSH24H")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPSH24H extends RTSPP {
	private static final long serialVersionUID = 1L;
	
}
