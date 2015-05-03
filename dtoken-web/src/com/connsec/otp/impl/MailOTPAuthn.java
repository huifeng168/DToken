package com.connsec.otp.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.connsec.config.EmailConfig;
import com.connsec.domain.UserInfo;
import com.connsec.otp.AbstractOTPAuthn;


public class MailOTPAuthn  extends AbstractOTPAuthn {
	private final static Logger logger = LoggerFactory.getLogger(MailOTPAuthn.class);
	EmailConfig emailConfig;
	
	public MailOTPAuthn(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public boolean produce(UserInfo userInfo) {
		try{
			String token=this.genToken(userInfo);
			Email email = new SimpleEmail();
			email.setHostName(emailConfig.getSmtpHost());
			email.setSmtpPort(emailConfig.getPort());
			email.setAuthenticator(new DefaultAuthenticator(emailConfig.getUsername(), emailConfig.getPassword()));
			email.setSSLOnConnect(emailConfig.isSsl());
			email.setFrom(emailConfig.getSenderMail());
			email.setSubject("One Time PassWord");
			email.setMsg("You Token is "+token+" , it validity in "+(interval/60) +" minutes");
			email.addTo(userInfo.getEmail());
			email.send();
			logger.debug("token "+token+" send to user +"+userInfo.getUsername()+", email "+userInfo.getEmail());
			this.insertDataBase(userInfo, token, userInfo.getUsername(), OPT_TYPES.EMAIL);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean validate(UserInfo userInfo,String token) {
		return this.validateDataBase(userInfo, token, OPT_TYPES.EMAIL);
	}

	
	/**
	 * @param emailConfig the emailConfig to set
	 */
	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}

	
}
