package com.connsec.otp.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.connsec.crypto.Base32Utils;
import com.connsec.domain.UserInfo;
import com.connsec.otp.AbstractOTPAuthn;
import com.connsec.otp.algorithm.HOTP;


public class HOTPOTPAuthn  extends AbstractOTPAuthn {
	private final static Logger logger = LoggerFactory.getLogger(HOTPOTPAuthn.class);

	boolean addChecksum;
	int truncation=-1;
	
	public HOTPOTPAuthn(JdbcTemplate jdbcTemplate) {
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
		String hotpToken;
		try {
			hotpToken = HOTP.generateOTP(byteSharedSecret, Long.parseLong(userInfo.getSharedCounter()), digits, addChecksum, truncation);
			logger.debug("token : "+token);
			logger.debug("hotpToken : "+hotpToken);
			if(token.equalsIgnoreCase(hotpToken)){
	        	return true;
	        }
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return false;
	}



	/**
	 * @return the addChecksum
	 */
	public boolean isAddChecksum() {
		return addChecksum;
	}

	/**
	 * @param addChecksum the addChecksum to set
	 */
	public void setAddChecksum(boolean addChecksum) {
		this.addChecksum = addChecksum;
	}

	/**
	 * @return the truncation
	 */
	public int getTruncation() {
		return truncation;
	}

	/**
	 * @param truncation the truncation to set
	 */
	public void setTruncation(int truncation) {
		this.truncation = truncation;
	}
	
	
	
}
