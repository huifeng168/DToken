package com.connsec.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;


/**
 * BaseDomain for connsec's domain
 * 
 * @author Crystal.sea
 * 
 */
public class BaseDomain extends Pagination implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6290127045507211154L;
	
	
	protected final static Logger logger = Logger.getLogger(BaseDomain.class);
	
	/**
	 * Domain id
	 */
	protected String 	id;
	

	
	protected String 	tid;
	
	protected String 	tname;
	/**
	 * description
	 */
	protected String  	description;
	
	protected int 		status;
	
	protected int 		sortOrder;
	
	protected String 	createdBy;
	
	protected Date 		createdDate;
	
	protected String 	modifiedBy;
	
	protected Date 		modifiedDate;
	
	
	protected String 	startDate;
	protected String 	endDate;
	
	public BaseDomain() {
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}





	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}



	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * @return the tname
	 */
	public String getTname() {
		return tname;
	}

	/**
	 * @param tname the tname to set
	 */
	public void setTname(String tname) {
		this.tname = tname;
	}

	/**
	 * @return the sortOrder
	 */
	public int getSortOrder() {
		return sortOrder;
	}




	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}




	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}




	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}




	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}




	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}




	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}




	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}




	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}




	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}




	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}




	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}




	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void genId() {
		this.id=UUID.randomUUID().toString().toLowerCase();
	}

	@Override
	public String toString() {
		return "BaseDomain [id=" + id + ", tid=" + tid + ", tname=" + tname
				+ ", status=" + status + ", sortOrder=" + sortOrder
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
