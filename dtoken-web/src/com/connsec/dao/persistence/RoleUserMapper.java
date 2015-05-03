/**
 * 
 */
package com.connsec.dao.persistence;

import java.util.List;

import com.connsec.db.persistence.IBaseMapper;
import com.connsec.domain.RoleUser;
import com.connsec.domain.UserInfo;


/**
 * @author Crystal.Sea
 *
 */

public  interface RoleUserMapper extends IBaseMapper<RoleUser> {
	
	public List<UserInfo> gridUserInfoInRole(RoleUser roleUser);
	
	public Integer countUserInfoInRole(RoleUser roleUser);
	
	public List<UserInfo> gridAllUserInfoInRole(RoleUser roleUser);
	
	public Integer countAllUserInfoInRole(RoleUser roleUser);
	
	public List<UserInfo> gridUserInfoNotInRole(RoleUser roleUser);
	
	public Integer countUserInfoNotInRole(RoleUser roleUser);
}
