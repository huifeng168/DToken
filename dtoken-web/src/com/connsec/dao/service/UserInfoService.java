package com.connsec.dao.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.connsec.dao.persistence.UserInfoMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.UserInfo;
import com.connsec.util.StringUtils;
import com.connsec.web.WebContext;


/**
 * @author Crystal.Sea
 *
 */
@Service
public class UserInfoService extends BaseService<UserInfo> {
	@Autowired
	private ShaPasswordEncoder passwordEncoder;
	
	public UserInfoService() {
		super(UserInfoMapper.class);
	}

	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public UserInfoMapper getMapper() {
		// TODO Auto-generated method stub
		return (UserInfoMapper)super.getMapper();
	}
	


	public boolean updateProtectedApps(UserInfo userinfo) {
		try {
			if(WebContext.getUserInfo() != null) {
				userinfo.setModifiedBy(WebContext.getUserInfo().getId());
			}
			//userinfo.setPwdUpdateDate(DateUtils.format(new Date(), DateUtils.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS));
			userinfo.setModifiedDate(new Date());
			return getMapper().updateProtectedApps(userinfo) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public UserInfo loadByAppIdAndUsername(String appId,String username){
		try {
			UserInfo userinfo = new UserInfo();
			userinfo.setUsername(username);
			return getMapper().loadByAppIdAndUsername(userinfo) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void logisticDeleteAllByCid(String cid){
		try {
			 getMapper().logisticDeleteAllByCid(cid);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean changePassword(UserInfo userInfo) {
		try {
			if(WebContext.getUserInfo() != null) {
				userInfo.setModifiedBy(WebContext.getUserInfo().getId());
				
				String password = passwordEncoder.encodePassword(userInfo.getPassword(), userInfo.getUsername());
		
				userInfo.setPassword(password);
				
			}
			//userinfo.setPwdUpdateDate(DateUtils.format(new Date(), DateUtils.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS));
			userInfo.setModifiedDate(new Date());
			return getMapper().changePassword(userInfo) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changeAppLoginPassword(UserInfo userinfo) {
		try {
			if(WebContext.getUserInfo() != null) {
				userinfo.setModifiedBy(WebContext.getUserInfo().getId());
			}
			//userinfo.setPwdUpdateDate(DateUtils.format(new Date(), DateUtils.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS));
			userinfo.setModifiedDate(new Date());
			return getMapper().changeAppLoginPassword(userinfo) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 锁定用户：islock：1 用户解锁 2 用户锁定
	 * @param userInfo
	 */
	public void locked(UserInfo userInfo) {
		try {
			if(userInfo != null && StringUtils.isNotEmpty(userInfo.getId())) {
				getMapper().locked(userInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户登录成功后，重置错误密码次数和解锁用户
	 * @param userInfo
	 */
	public void unlock(UserInfo userInfo) {
		try {
			if(userInfo != null && StringUtils.isNotEmpty(userInfo.getId())) {
				getMapper().unlock(userInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新错误密码次数
	 * @param userInfo
	 */
	public void updateBadPasswordCount(UserInfo userInfo) {
		try {
			if(userInfo != null && StringUtils.isNotEmpty(userInfo.getId())) {
				getMapper().updateBadPWDCount(userInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean changeSharedSecret(UserInfo userInfo){
		return getMapper().changeSharedSecret(userInfo)>0;
	}
	
	public boolean changePasswordQuestion(UserInfo userInfo){
		return getMapper().changePasswordQuestion(userInfo)>0;
	}
	
	public boolean changeAuthnType(UserInfo userInfo){
		return getMapper().changeAuthnType(userInfo)>0;
	}
	
	public boolean changeEmail(UserInfo userInfo){
		return getMapper().changeEmail(userInfo)>0;
	}
	
	public boolean changeMobile(UserInfo userInfo){
		return getMapper().changeMobile(userInfo)>0;
	}
	
}
