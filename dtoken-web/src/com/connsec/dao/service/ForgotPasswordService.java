package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.ForgotPasswordMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.ForgotPassword;
import com.connsec.domain.UserInfo;

@Service
public class ForgotPasswordService  extends BaseService<ForgotPassword>{

	public ForgotPasswordService() {
		super(ForgotPasswordMapper.class);
	}

	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public ForgotPasswordMapper getMapper() {

		return (ForgotPasswordMapper)super.getMapper();
	}

	
	public UserInfo queryUserInfoByEmail(String email){
		return getMapper().queryUserInfoByEmail(email);
	}
	
}
