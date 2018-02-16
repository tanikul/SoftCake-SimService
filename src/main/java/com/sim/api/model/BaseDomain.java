package com.sim.api.model;

import java.util.Date;

public class BaseDomain {

	private String createdBy;
	private Date createdDate;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdateBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdateDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
}
