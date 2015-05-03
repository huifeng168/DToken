package com.connsec.dao.persistence;

import com.connsec.db.persistence.IBaseMapper;
import com.connsec.domain.UserInfo;


/**
 * @author Crystal.Sea
 *
 */
public interface UserInfoMapper  extends IBaseMapper<UserInfo>{
	
	//login query
	
	public UserInfo loadByAppIdAndUsername(UserInfo userInfo);
	
	public int logisticDeleteAllByCid(String cid);
	
	public void locked(UserInfo userInfo);

	public void unlock(UserInfo userInfo);

	public void updateBadPWDCount(UserInfo userInfo);
	
	public int changePassword(UserInfo userInfo);
	
	public int changeAppLoginPassword(UserInfo userInfo);
	
	public int updateProtectedApps(UserInfo userInfo);
	
	public int changeSharedSecret(UserInfo userInfo);
	
	public int changePasswordQuestion(UserInfo userInfo);
	
	public int changeAuthnType(UserInfo userInfo);
	
	public int changeEmail(UserInfo userInfo);
	
	public int changeMobile(UserInfo userInfo);
	
	
	
}
