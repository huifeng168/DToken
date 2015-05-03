package com.connsec.otp.impl;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.connsec.crypto.Base32Utils;
import com.connsec.domain.UserInfo;
import com.connsec.otp.AbstractOTPAuthn;
import com.connsec.otp.algorithm.TimeBasedOTP;


public class CounterBasedOTPAuthn  extends AbstractOTPAuthn {
	private final static Logger logger = LoggerFactory.getLogger(CounterBasedOTPAuthn.class);
	
	public CounterBasedOTPAuthn(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public boolean produce(UserInfo userInfo) {
		return true;
	}

	@Override
	public boolean validate(UserInfo userInfo,String token) {
		logger.debug("SharedCounter : "+userInfo.getSharedCounter());
		byte[]byteSharedSecret= Base32Utils.decode(userInfo.getSharedSecret());
        String hexSharedSecret=Hex.encodeHexString(byteSharedSecret);
        String counterBasedToken="";
        if(crypto.equalsIgnoreCase("HmacSHA1")){
        	counterBasedToken=TimeBasedOTP.genOTP(hexSharedSecret,userInfo.getSharedCounter(),""+digits);
        }else  if(crypto.equalsIgnoreCase("HmacSHA256")){
        	counterBasedToken=TimeBasedOTP.genOTPHmacSHA256(hexSharedSecret,userInfo.getSharedCounter(),""+digits);
        }else  if(crypto.equalsIgnoreCase("HmacSHA512")){
        	counterBasedToken=TimeBasedOTP.genOTPHmacSHA512(hexSharedSecret,userInfo.getSharedCounter(),""+digits);
        }
        
        logger.debug("token : "+token);
        logger.debug("counterBasedToken : "+counterBasedToken);
        if(token.equalsIgnoreCase(counterBasedToken)){
        	return true;
        }
       	return false;
	}
	
}
