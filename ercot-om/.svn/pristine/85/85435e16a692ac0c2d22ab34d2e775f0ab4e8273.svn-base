package com.softwarelikeyou.model.entity.rtspp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.Daily;

@Entity
@Table(name="rtsppdaily", uniqueConstraints = @UniqueConstraint(columnNames = { "type", "name", "intervalDate" }))
@XmlRootElement(name="rtsppdaily")
@Path("/rtsppdaily")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTSPPDaily extends Daily {
	private static final long serialVersionUID = 1L;

}
