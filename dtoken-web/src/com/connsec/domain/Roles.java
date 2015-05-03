package com.connsec.domain;

import java.io.Serializable;



/*
   ID                   varchar(40)                    not null,
   NAME                 varchar(60)                    not null,
   STATUS               char(1)                        null,
   CREATEBY             varchar(40)                    null,
   CREATEDATE           date                           null,
   UPDATEBY             varchar(40)                    null,
   UPDATEDATE           date                           null,
   constraint PK_ROLES primary key clustered (ID)
 */
/**
 * @author Crystal.Sea
 *
 */
public class Roles extends BaseDomain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6928570405840778151L;
	
	
	private String name;
	private String navsId;

	
	
	public Roles() {
		super();
	}


	public Roles(String name, String navsId) {
		super();
		this.name = name;
		this.navsId = navsId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNavsId() {
		return navsId;
	}
	public void setNavsId(String navsId) {
		this.navsId = navsId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Roles [name=" + name + ", navsId=" + navsId + "]";
	}
	
	

}
