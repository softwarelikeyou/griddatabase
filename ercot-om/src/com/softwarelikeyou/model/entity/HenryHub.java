package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HenryHub")
public class HenryHub  implements Serializable {	
	private static final long serialVersionUID = 1L; 
	
	public HenryHub() { }
	
	@Id
	@Column(name="date", nullable=false)
	private Date date = new Date();
	public Date getDate() {
		return date;
	}
	public void setDate(final Date value) {
		this.date = value;
	}

	@Column(name="price", precision=7, scale=2)
	private float price = 0f;	
	public Float getPrice() {
		return price;
	}
	public void setPrice(final Float value) {
		this.price = value;
	}
}
