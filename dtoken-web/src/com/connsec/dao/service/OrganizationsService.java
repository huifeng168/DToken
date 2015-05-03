package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.OrganizationsMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.Organizations;


@Service
public class OrganizationsService  extends BaseService<Organizations>{

	public OrganizationsService() {
		super(OrganizationsMapper.class);
		
	}
	
	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public OrganizationsMapper getMapper() {
		// TODO Auto-generated method stub
		return (OrganizationsMapper)super.getMapper();
	}
}
