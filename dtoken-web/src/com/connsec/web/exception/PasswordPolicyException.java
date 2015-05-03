package com.connsec.web.exception;

import com.connsec.web.WebContext;

public class PasswordPolicyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -253274228039876768L;
	private String errorCode;
	private Object filedValue;
	public PasswordPolicyException(String errorCode,Object filedValue) {
		super();
		this.errorCode = errorCode;
		this.filedValue = filedValue;
	}
	public PasswordPolicyException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	public Object getFiledValue() {
		return filedValue;
	}

	public String getKey() {
		return "message.passwordpolicy."+errorCode.toLowerCase();
	}
	public String getErrorCode() {
		return errorCode;
	}


	
}
