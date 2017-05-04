package com.softwarelikeyou.model.entity.rtlmp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

@Deprecated
 
public class RTLMPInterval {

	@Column(name = "intervalId", length=3)
	private int intervalId;
	
	@Column(name = "intervalEnding", nullable = false)
	private Date intervalEnding;
	
	@Column(name = "LMP", precision=7, scale=2)
	private float LMP;
	
	public int getIntervalId() {
		return intervalId;
	}
	public void setIntervalId(int intervalId) {
		this.intervalId = intervalId;
	}
	public Date getIntervalEnding() {
		return intervalEnding;
	}
	public void setIntervalEnding(Date intervalEnding) {
		this.intervalEnding = intervalEnding;
	}
	public float getLMP() {
		return LMP;
	}
	public void setLMP(float lMP) {
		LMP = lMP;
	}
	
}
