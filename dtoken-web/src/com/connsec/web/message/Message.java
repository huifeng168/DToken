/**
 * 
 */
package com.connsec.web.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.connsec.web.WebContext;

/**
 * message类定义
 * @author Crystal.Sea
 *
 */
public class Message {
	final static Logger log = Logger.getLogger(Message.class);
	//服务名称
	private String serviceName;
	//信息内容
	private String message;
	//信息代码
	private String code;
	//信息对象
	private Object messageObject;
	//错误信息
	private ArrayList<HashMap<String,Object>> errors;
	//类型
	private MessageType messageType=MessageType.info;
	//操作类型
	private OperateType operateType=OperateType.unknown;
	//范围
	MessageScope messageScope=MessageScope.JSON;
	
	public Message() {
		
	}
	
	public Message(String message) {
		this.message = message;
		this.messageType = MessageType.info;
	}
	
	
	public Message(BindingResult result) {
		setFieldErrors(result);
	}

	public Message(String message, String code) {
		this.message = message;
		this.code = code;
		this.messageType = MessageType.info;
	}
	
	public Message(String message, MessageType messageType) {
		this.message = message;
		this.messageType = messageType;
	}
	
	public Message(String message,BindingResult result) {
		this.message = message;
		setFieldErrors(result);
	}
	
	public Message(String message, String code, MessageType messageType) {
		this.message = message;
		this.code = code;
		this.messageType = messageType;
	}
	
	public Message(String message,Object messageObject, MessageType messageType,
			 OperateType operateType) {
		this.message = message;
		this.messageType = messageType;
		this.operateType = operateType;
		this.messageObject = messageObject;
	}
	
	
	public Message(String message,Object messageObject, MessageType messageType,
			 OperateType operateType,MessageScope messageScope) {
		this.message = message;
		this.messageObject = messageObject;
		this.messageType = messageType;
		this.operateType = operateType;
		this.messageScope= messageScope;
	}
	
	public Message(String message,Object messageObject,BindingResult result, MessageType messageType,
			 OperateType operateType,MessageScope messageScope) {
		this.message = message;
		this.messageObject = messageObject;
		this.operateType = operateType;
		this.messageScope= messageScope;
		setFieldErrors(result);
		this.messageType = messageType;
	}
	
	public Message(String serviceName,String message,Object messageObject,BindingResult result, MessageType messageType,
			 OperateType operateType,MessageScope messageScope) {
		this.serviceName	=	serviceName;
		this.message = message;
		this.messageObject = messageObject;
		this.operateType = operateType;
		this.messageScope= messageScope;
		setFieldErrors(result);
		this.messageType = messageType;
	}

	public Message(String serviceName,String message,Object messageObject,BindingResult result, MessageType messageType,
			 OperateType operateType,MessageScope messageScope,String code) {
		this(serviceName, message, messageObject, result, messageType, operateType, messageScope);
		this.code = code;
	}

	/**
	 * 验证错误组装
	 * @param result
	 */
	public void setFieldErrors(BindingResult result) {
		if(result==null)
			return;
		this.messageType = MessageType.error;
		this.errors=new ArrayList<HashMap<String,Object>>();
		List<FieldError> listFieldError=result.getFieldErrors();
		
		for(FieldError fieldError : listFieldError){
			HashMap<String,Object> error=new HashMap<String,Object>();
			error.put("field", fieldError.getField());
			error.put("type", fieldError.getCode());
			error.put("objectName", fieldError.getObjectName());
			String defaultMessageSourceResolvable=fieldError.getCodes()[0];
			String errorMessage=(defaultMessageSourceResolvable);
			if(errorMessage==null){
				error.put("message", /*fieldError.getField()+" "+*/fieldError.getDefaultMessage());
			}else{
				error.put("message", errorMessage);
			}
			log.debug(error);
			this.errors.add(error);
		}
		log.debug(this.errors);
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public ArrayList<HashMap<String, Object>> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<HashMap<String, Object>> errors) {
		this.errors = errors;
	}

	public OperateType getOperateType() {
		return operateType;
	}

	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}

	public Object getMessageObject() {
		return messageObject;
	}

	public void setMessageObject(Object messageObject) {
		this.messageObject = messageObject;
	}

	public MessageScope getMessageScope() {
		return messageScope;
	}

	public void setMessageScope(MessageScope messageScope) {
		this.messageScope = messageScope;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
}
