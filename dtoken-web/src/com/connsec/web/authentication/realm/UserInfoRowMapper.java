package com.connsec.web.authentication.realm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.connsec.domain.UserInfo;

public class UserInfoRowMapper  implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum)throws SQLException {
		
		UserInfo userInfo=new UserInfo();
		userInfo.setId(rs.getString("ID"));
		userInfo.setUsername(rs.getString("USERNAME"));
		userInfo.setPassword(rs.getString("PASSWORD"));
		userInfo.setSharedSecret(rs.getString("SHAREDSECRET"));
		userInfo.setSharedCounter(rs.getString("SHAREDCOUNTER"));
		userInfo.setUserType(rs.getString("USERTYPE"));
		
		userInfo.setDisplayName(rs.getString("DISPLAYNAME"));
		userInfo.setNameZHShortSpell(rs.getString("NAMEZHSHORTSPELL"));//nameZHSpell
		userInfo.setGivenName(rs.getString("GIVENNAME"));
		userInfo.setMiddleName(rs.getString("MIDDLENAME"));
		userInfo.setFamilyName(rs.getString("FAMILYNAME"));
		
		userInfo.setGender(rs.getInt("GENDER"));
		
		userInfo.setMobile(rs.getString("MOBILE"));
		userInfo.setMobileVerified(rs.getInt("MOBILEVERIFIED"));
		userInfo.setEmail(rs.getString("EMAIL"));
		userInfo.setEmailVerified(rs.getInt("EMAILVERIFIED"));
	

		userInfo.setEmployeeNumber(rs.getString("EMPLOYEENUMBER"));
		userInfo.setDivision(rs.getString("DIVISION"));
		userInfo.setOrganization(rs.getString("ORGANIZATION"));
		userInfo.setDepartmentId(rs.getString("DEPARTMENTID"));
		userInfo.setDepartment(rs.getString("DEPARTMENT"));
		userInfo.setJobTitle(rs.getString("JOBTITLE"));
		
		
		
		userInfo.setCreatedBy(rs.getString("CREATEDBY"));
		userInfo.setCreatedDate(rs.getTimestamp("CREATEDDATE"));
		userInfo.setModifiedBy(rs.getString("MODIFIEDBY"));
		userInfo.setModifiedDate(rs.getTimestamp("MODIFIEDDATE"));
		
		userInfo.setStatus(rs.getInt("STATUS"));
		userInfo.setDescription(rs.getString("DESCRIPTION"));
		
		return userInfo;
	}	
}
