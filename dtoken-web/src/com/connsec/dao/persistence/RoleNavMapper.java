/**
 * 
 */
package com.connsec.dao.persistence;

import java.util.List;

import com.connsec.db.persistence.IBaseMapper;
import com.connsec.domain.Navigations;
import com.connsec.domain.RoleNav;


/**
 * @author Crystal.Sea
 *
 */

public  interface RoleNavMapper extends IBaseMapper<RoleNav> {
	
	public List<Navigations> queryNavs(String roleId);
	public int deleteRoleNav(String roleId);
	public int insertRoleNav(List<RoleNav> listRoleNav);
	
}
