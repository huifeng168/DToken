package com.connsec.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;



/*
   ID                   varchar(40)                    not null,
   ROLEID               varchar(40)                    null,
   MENUID                varchar(40)	                   null
   constraint PK_ROLES primary key clustered (ID)
 */
/**
 * @author Crystal.Sea
 *
 */
public class RoleNav extends BaseDomain implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3461258339474457017L;
	
	
	@NotEmpty
	private String roleId;
	@NotEmpty
	private String navId;
	
	public RoleNav(){
		super();
	}

	public RoleNav(String roleId, String navId) {
		super();
		this.roleId = roleId;
		this.navId = navId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getNavId() {
		return navId;
	}

	public void setNavId(String navId) {
		this.navId = navId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleNav [roleId=" + roleId + ", navId=" + navId + "]";
	}

	
}
