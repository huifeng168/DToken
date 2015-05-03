package com.connsec.domain;

import java.io.Serializable;


/**
 * @author Crystal.Sea
 * 
 */
public class Logs extends BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6560201093784960493L;
	String serviceName;
	String message;
	String content;
	String messageType;
	String operateType;
	String startDate;
	String endDate;
	String username;
	String code;

	public Logs() {
		super();
	}

	public Logs(String serviceName, String code, String message,
			String content, String messageType, String operateType,
			String createdBy, String username, String cname) {
		super();
		this.serviceName = serviceName;
		this.code = code;
		this.message = message;
		this.content = content;
		this.messageType = messageType;
		this.operateType = operateType;
		this.createdBy = createdBy;
		this.username = username;
		this.tname = cname;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Logs [serviceName=" + serviceName + ", message=" + message
				+ ", content=" + content + ", messageType=" + messageType
				+ ", operateType=" + operateType + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", username=" + username + ", code="
				+ code + "]";
	}

}
