package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.RolesMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.Roles;

@Service
public class RolesService extends BaseService<Roles> {

	public RolesService() {
		super(RolesMapper.class);

	}

	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public RolesMapper getMapper() {
		// TODO Auto-generated method stub
		return (RolesMapper)super.getMapper();
	}
	


}
