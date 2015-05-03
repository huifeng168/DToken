package com.connsec.web.authentication.realm.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.connsec.domain.UserInfo;
import com.connsec.web.authentication.realm.AbstractAuthenticationRealm;


/**
 * @author Crystal.Sea
 *
 */
public class JdbcAuthenticationRealm extends AbstractAuthenticationRealm{
	private static Log _log = LogFactory.getLog(JdbcAuthenticationRealm.class);
	
	@Autowired
	private MessageDigestPasswordEncoder passwordEncoder;
	

	public JdbcAuthenticationRealm() {
	
	}
	
	public JdbcAuthenticationRealm(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	public UserInfo loadUserInfo(String username, String j_password) {
		UserInfo userInfo=super.loadUserInfo(username, j_password);
		
		return userInfo;
	}

	@Override
	public boolean passwordValid(UserInfo userInfo, String j_password) {
		boolean passwordvalid=false;
		_log.info("password : "+passwordEncoder.encodePassword(j_password, userInfo.getUsername()));
    	passwordvalid= passwordEncoder.isPasswordValid( userInfo.getPassword(),j_password, userInfo.getUsername());
    	_log.debug("passwordvalid : "+passwordvalid);
    	if(!passwordvalid){
    		setBadPasswordCount(userInfo);
    		throw new BadCredentialsException("passwordvalid is "+passwordvalid);
    	}
    	return passwordvalid;
	}
}
