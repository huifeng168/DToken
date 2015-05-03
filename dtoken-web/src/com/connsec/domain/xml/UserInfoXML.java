package com.connsec.domain.xml;

import javax.xml.bind.annotation.XmlRootElement;

import com.connsec.domain.BaseDomain;
/**
 * xml can not include array , MultipartFile
 * @author Crystal.Sea
 *
 */
@XmlRootElement
public class UserInfoXML extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6942731467730249291L;

	/**
	 * 
	 */
	public UserInfoXML() {
		super();
		// TODO Auto-generated constructor stub
	}



}
