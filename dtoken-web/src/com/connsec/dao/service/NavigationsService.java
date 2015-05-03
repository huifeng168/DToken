package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.NavigationsMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.Navigations;


@Service
public class NavigationsService  extends BaseService<Navigations>{

	public NavigationsService() {
		super(NavigationsMapper.class);
		
	}
	
	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public NavigationsMapper getMapper() {
		// TODO Auto-generated method stub
		return (NavigationsMapper)super.getMapper();
	}

}
