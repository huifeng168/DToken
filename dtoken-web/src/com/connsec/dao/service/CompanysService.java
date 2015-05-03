package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.CompanysMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.Companys;

@Service
public class CompanysService  extends BaseService<Companys>{

	public CompanysService() {
		super(CompanysMapper.class);
	}

	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public CompanysMapper getMapper() {
		// TODO Auto-generated method stub
		return (CompanysMapper)super.getMapper();
	}
	
}
