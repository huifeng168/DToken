package com.connsec.otp.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.connsec.domain.UserInfo;
import com.connsec.otp.AbstractOTPAuthn;

/**
 * Chip Authentication Program
 * EMV stands for Europay, MasterCard and Visa,
 * a global standard for inter-operation of integrated circuit cards (IC cards or "chip cards") 
 * and IC card capable point of sale (POS) terminals and automated teller machines (ATMs), 
 * for authenticating credit and debit card transactions.
 * 
 * @author Crystal.Sea
 *
 */
public class CAPOTPAuthn  extends AbstractOTPAuthn {

	public CAPOTPAuthn(JdbcTemplate jdbcTemplate) {
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
