package com.softwarelikeyou.model.entity.ascpc;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.softwarelikeyou.model.entity.Daily;

@Entity
@Table(name="ascpcdaily", uniqueConstraints = @UniqueConstraint(columnNames = { "type", "name", "intervalDate" }))
@XmlRootElement(name="ascpcdaily")
@Path("/ascpcdaily")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASCPCDaily extends Daily {
	private static final long serialVersionUID = 1L;

}
