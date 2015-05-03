package com.connsec.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.RoleNavMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.Navigations;
import com.connsec.domain.RoleNav;

@Service
public class RoleNavService extends BaseService<RoleNav> {

	public RoleNavService() {
		super(RoleNavMapper.class);

	}

	
	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public RoleNavMapper getMapper() {
		// TODO Auto-generated method stub
		return (RoleNavMapper)super.getMapper();
	}

	
	public List<Navigations> queryNavs(String roleId){
		return getMapper().queryNavs(roleId);
	}
	
	
	public boolean delete(String roleId) {
		return  getMapper().deleteRoleNav(roleId) > 0;
	}

	public boolean insert(List<RoleNav> listRoleNav) {
		return getMapper().insertRoleNav(listRoleNav)>0;
	}
}
