package com.connsec.dao.service;

import org.springframework.stereotype.Service;

import com.connsec.constants.DEFAULTROLE;
import com.connsec.dao.persistence.RoleUserMapper;
import com.connsec.db.service.BaseService;
import com.connsec.domain.RoleUser;
import com.connsec.domain.UserInfo;
import com.connsec.web.component.Grid;

@Service
public class RoleUserService extends BaseService<RoleUser> {

	public RoleUserService() {
		super(RoleUserMapper.class);

	}

	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public RoleUserMapper getMapper() {
		// TODO Auto-generated method stub
		return (RoleUserMapper)super.getMapper();
	}

	
	public boolean insertTenantAdmin(String uid) {
		RoleUser roleUser =new RoleUser(DEFAULTROLE.TANANT_ADMIN,uid);
		roleUser.genId();
		return insert(roleUser);
	}
	
	
	
	public boolean deleteTenantAdmin(String uid) {
		RoleUser roleUser =new RoleUser(DEFAULTROLE.TANANT_ADMIN,uid);
		return  delete(roleUser);
	}
	

	public Grid<UserInfo> gridAllUserInfoInRole(RoleUser roleUser) {
		Integer totalCount = parseCount(getMapper().countAllUserInfoInRole(roleUser));
		if(totalCount == 0) {
			return new Grid<UserInfo>();
		}
		
		int totalPage = calculateTotalPage(roleUser,totalCount);
		
		if(totalPage == 0) {
			return new Grid<UserInfo>();
		}
		
		if(totalPage < roleUser.getPage()) {
			roleUser.setPage(totalPage);
			roleUser.setStartRow(calculateStartRow(totalPage,roleUser.getPageResults()));
		}
		return new Grid<UserInfo>(roleUser.getPage(),roleUser.getPageResults(),totalCount,getMapper().gridAllUserInfoInRole(roleUser));
	}
	
	public Grid<UserInfo> gridUserInfoInRole(RoleUser roleUser) {
		Integer totalCount = parseCount(getMapper().countUserInfoInRole(roleUser));
		if(totalCount == 0) {
			return new Grid<UserInfo>();
		}
		
		int totalPage = calculateTotalPage(roleUser,totalCount);
		
		if(totalPage == 0) {
			return new Grid<UserInfo>();
		}
		
		if(totalPage < roleUser.getPage()) {
			roleUser.setPage(totalPage);
			roleUser.setStartRow(calculateStartRow(totalPage,roleUser.getPageResults()));
		}
		return new Grid<UserInfo>(roleUser.getPage(),roleUser.getPageResults(),totalCount,getMapper().gridUserInfoInRole(roleUser));
	}
	
	public Grid<UserInfo> gridUserInfoNotInRole(RoleUser roleUser) {
		Integer totalCount = parseCount(getMapper().countUserInfoNotInRole(roleUser));
		int totalPage = calculateTotalPage(roleUser,totalCount);
		if(totalPage == 0) {
			return new Grid<UserInfo>();
		}
		if(totalPage < roleUser.getPage()) {
			roleUser.setPage(totalPage);
			roleUser.setStartRow(calculateStartRow(totalPage,roleUser.getPageResults()));
		}
		return new Grid<UserInfo>(roleUser.getPage(),roleUser.getPageResults(),totalCount,getMapper().gridUserInfoNotInRole(roleUser));
	}
}
