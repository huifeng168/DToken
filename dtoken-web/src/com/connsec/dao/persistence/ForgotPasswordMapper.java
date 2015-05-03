/**
 * 
 */
package com.connsec.dao.persistence;

import com.connsec.db.persistence.IBaseMapper;
import com.connsec.domain.ForgotPassword;
import com.connsec.domain.UserInfo;

/**
 * @author Crystal.sea
 *
 */

public  interface ForgotPasswordMapper extends IBaseMapper<ForgotPassword> {
	
	public UserInfo queryUserInfoByEmail(String email);
	
}
