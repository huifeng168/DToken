package com.connsec.otp.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.connsec.domain.UserInfo;
import com.connsec.otp.AbstractOTPAuthn;


public class MobileOTPAuthn  extends AbstractOTPAuthn {

	public MobileOTPAuthn(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public boolean produce(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validate(UserInfo userInfo,String token) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
}
