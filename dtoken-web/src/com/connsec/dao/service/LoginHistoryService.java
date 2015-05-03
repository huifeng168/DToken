package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.LoginHistoryMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.LoginHistory;

@Service
public class LoginHistoryService  extends BaseService<LoginHistory>{

	public LoginHistoryService() {
		super(LoginHistoryMapper.class);
	}
	
	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public LoginHistoryMapper getMapper() {
		// TODO Auto-generated method stub
		return (LoginHistoryMapper)super.getMapper();
	}
}
