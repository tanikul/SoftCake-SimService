package com.sim.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Sim extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -6487278368350499450L;

	private String periodType;
	private BigDecimal price;
	private String activeStatus;
	private String simNumber;
	
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getSimNumber() {
		return simNumber;
	}
	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	
}
