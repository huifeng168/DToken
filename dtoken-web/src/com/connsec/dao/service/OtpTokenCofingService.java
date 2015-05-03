package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.OtpTokenCofingMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.OtpTokenCofing;

@Service
public class OtpTokenCofingService  extends BaseService<OtpTokenCofing>{

	public OtpTokenCofingService() {
		super(OtpTokenCofingMapper.class);
	}

	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public OtpTokenCofingMapper getMapper() {
		// TODO Auto-generated method stub
		return (OtpTokenCofingMapper)super.getMapper();
	}
	
}
