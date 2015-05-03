package com.connsec.domain;

import com.connsec.domain.BaseDomain;


public class Organizations   extends BaseDomain implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 380133339153668236L;
	private String code;
	private String name;
	private String fullName;
	private String pId;
	private String pName;
	private String type;
	private String xPath;
	private String level;
	private String hasChild;
	
	private String division;
	

	// Constructors

	/** default constructor */
	public Organizations() {
	}
	
	public Organizations(String id) {
		this.id = id;
	}

	/** minimal constructor */
	public Organizations(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getxPath() {
		return xPath;
	}

	public void setxPath(String xPath) {
		this.xPath = xPath;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Override
	public String toString() {
		return "Organizations [code=" + code + ", name=" + name + ", fullName="
				+ fullName + ", pId=" + pId + ", pName=" + pName + ", type="
				+ type + ", xPath=" + xPath + ", level=" + level
				+ ", hasChild=" + hasChild + ", division=" + division + "]";
	}


}