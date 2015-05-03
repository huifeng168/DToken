package com.connsec.web.authentication.realm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.connsec.domain.Navigations;
import com.connsec.domain.Roles;
import com.connsec.domain.UserInfo;
import com.connsec.util.DateUtils;
import com.connsec.web.WebContext;
import com.connsec.web.WebConstants;


/**
 * @author Crystal.Sea
 *
 */
public abstract class AbstractAuthenticationRealm{
	private static Log _log = LogFactory.getLog(AbstractAuthenticationRealm.class);
	
	private static final String HISTORY_LOGIN_INSERT_STATEMENT = "INSERT INTO LOGIN_HISTORY (ID , SESSIONID , UID , USERNAME , DISPLAYNAME , LOGINTYPE , MESSAGE , CODE , PROVIDER , SOURCEIP , BROWSER , PLATFORM , APPLICATION , LOGINURL )VALUES( ? , ? , ? , ? , ?, ? , ? , ?, ? , ? , ?, ? , ? , ?)";
	
	
	private static final String NAVIGATIONS_SELECT_STATEMENT = "SELECT DISTINCT N.* FROM ROLE_NAV RN, NAVIGATIONS N WHERE  RN.ROLEID IN(SELECT R.ID FROM ROLES R WHERE ( R.ID='ORDINARY_USER' OR R.ID IN(SELECT ROLEID FROM  USERINFO U, ROLE_USER RU WHERE U.ID = ? AND U.ID = RU.UID AND U.STATUS = 1)) AND R.STATUS = 1) AND RN.NAVID=N.ID AND N.STATUS = 1 ORDER BY PID, SORTORDER";
	
	private static final String ROLES_SELECT_STATEMENT = "SELECT DISTINCT R.ID,R.NAME FROM USERINFO U,ROLES R,ROLE_USER RU WHERE U.ID = ?  AND U.ID=RU.UID AND RU.ROLEID=R.ID AND	 R.STATUS<>'2'";
	
	private static final String DEFAULT_USERINFO_SELECT_STATEMENT = "SELECT * FROM	USERINFO WHERE USERNAME = ?";
	
	
	protected  JdbcTemplate jdbcTemplate;
	
	

 	/**
	 * 
	 */
	public AbstractAuthenticationRealm() {
		
	}
	
	
	public AbstractAuthenticationRealm(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}


	
	public boolean passwordPolicyValid(UserInfo userInfo){

		
		return true;
	}
	
	public UserInfo loadUserInfo(String username,String j_password) {
		List<UserInfo> listUserInfo=jdbcTemplate.query(
				DEFAULT_USERINFO_SELECT_STATEMENT,
				new UserInfoRowMapper(),
				username);
		UserInfo userInfo=null;
		if(listUserInfo!=null&&listUserInfo.size()>0){
			userInfo=listUserInfo.get(0);
		}
		_log.debug("load UserInfo : "+userInfo);
		return userInfo;
	}

	public abstract boolean passwordValid(UserInfo userInfo,String j_password);
	

	public static boolean isAuthenticated(){
 		if(WebContext.getUserInfo()!=null){
 			return true;
 		}else{
 			return false;
 		}
 	}
	


	/**
	 * 更新错误密码次数
	 * @param userInfo
	 */
	public void setBadPasswordCount(UserInfo userInfo) {
	
	}
	
	
	public List<Navigations> queryNavs(UserInfo userInfo){
		List<Navigations> listNavigations=jdbcTemplate.query(NAVIGATIONS_SELECT_STATEMENT, new RowMapper<Navigations>() {
			public Navigations mapRow(ResultSet rs, int rowNum) throws SQLException {
				Navigations navigation=new Navigations();
				navigation.setId(rs.getString("ID"));
				navigation.setName(rs.getString("NAME"));
				navigation.setUrl(rs.getString("URL"));
				navigation.setType(rs.getString("TYPE"));
				navigation.setTarget(rs.getString("TARGET"));
				navigation.setpId(rs.getString("PID"));
				navigation.setpName(rs.getString("PNAME"));
				navigation.setxPath(rs.getString("XPATH"));
				navigation.setHasChild(rs.getString("HASCHILD"));
				navigation.setSortOrder(rs.getInt("SORTORDER"));
				navigation.setVisible(rs.getInt("VISIBLE"));
				return navigation;
			}
		},userInfo.getId());
		
		_log.debug("list Navigations "+listNavigations);
		
		return listNavigations;
	}
	
	public List<Roles> queryRoles(UserInfo userInfo) {
		List<Roles> listRoles=jdbcTemplate.query(ROLES_SELECT_STATEMENT, new RowMapper<Roles>() {
			public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
				Roles role=new Roles();
				role.setId(rs.getString("ID"));
				role.setName(rs.getString("NAME"));
				return role;
			}
		},userInfo.getId());
		
		_log.debug("list Roles  "+listRoles);
		return listRoles;
	}
	
	
    
    /**
     * Granted Authority And Navs by userInfo
     * @param userInfo
     * @return ArrayList<GrantedAuthority> 
     */
	public ArrayList<GrantedAuthority> grantAuthorityAndNavs(UserInfo userInfo){
    	//call grantAuthority
		ArrayList<GrantedAuthority> grantedAuthority = grantAuthority(userInfo);
		//call grantNavs
		grantNavs(userInfo);
		
		return grantedAuthority;
    }
    
    /**
     * grant Authority by userinfo
     * @param userInfo
     * @return ArrayList<GrantedAuthority> 
     */
	public ArrayList<GrantedAuthority> grantAuthority(UserInfo userInfo){
    	//query roles for user
    	List<Roles> listRoles=queryRoles(userInfo);
    	
    	//set role for spring security
		ArrayList<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthority.add(new SimpleGrantedAuthority("ORDINARY_USER"));
		for(Roles role :listRoles){
			grantedAuthority.add(new SimpleGrantedAuthority(role.getId()));
		}
		_log.debug("Authority : "+grantedAuthority);
		
		WebContext.setRoles(listRoles);
		return grantedAuthority;
    }
    

    /**
     * grant Navs by userinfo
     * @param userInfo
     * @return List<Menus>
     */
	public List<Navigations> grantNavs(UserInfo userInfo){
    	//query menus for user
    	List<Navigations> listNavs =queryNavs(userInfo);
		WebContext.setNavigations(listNavs);
		return listNavs;
    }
    

	   /**
     * login log write to log db
     * @param uid
     * @param j_username
     * @param type
     * @param code
     * @param message
     */
	public boolean insertLoginHistory(UserInfo userInfo,String type,String provider,String code,String message){
		String sessionId=WebContext.genId();
		WebContext.setAttribute(WebConstants.CURRENT_USER_SESSION_ID, sessionId);
		String ipAddress=WebContext.getRequestIpAddress();
		String platform="";
		String browser="";
		String userAgent = WebContext.getRequest().getHeader("User-Agent");  
    	String []arrayUserAgent=null;
    	if(userAgent.indexOf("MSIE")>0){
    		arrayUserAgent=userAgent.split(";");
    		browser=arrayUserAgent[1].trim();
    		platform=arrayUserAgent[2].trim();
    	}else if(userAgent.indexOf("Chrome")>0){
    		arrayUserAgent=userAgent.split(" ");
    		//browser=arrayUserAgent[8].trim();
    		for(int i=0;i<arrayUserAgent.length;i++){
    			if(arrayUserAgent[i].contains("Chrome")){
    				browser=arrayUserAgent[i].trim();
    			}
    		}
    		platform=(arrayUserAgent[1].substring(1)+" "+arrayUserAgent[2]+" "+arrayUserAgent[3].substring(0, arrayUserAgent[3].length()-1)).trim();
    	}else if(userAgent.indexOf("Firefox")>0){
    		arrayUserAgent=userAgent.split(" ");
    		for(int i=0;i<arrayUserAgent.length;i++){
    			if(arrayUserAgent[i].contains("Firefox")){
    				browser=arrayUserAgent[i].trim();
    			}
    		}
    		platform=(arrayUserAgent[1].substring(1)+" "+arrayUserAgent[2]+" "+arrayUserAgent[3].substring(0, arrayUserAgent[3].length()-1)).trim();
    		
    	}
    	
		jdbcTemplate.update(HISTORY_LOGIN_INSERT_STATEMENT, 
				new Object[] { 
					WebContext.genId(),
					sessionId,
					userInfo.getId(),
					userInfo.getUsername(),
					userInfo.getDisplayName(),
					type,
					message,
					code,
					provider,
					ipAddress,
					browser,
					platform,
					"Browser",
					new Date()},
				new int[] {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.TIMESTAMP });
		
		
		return true;
	}
	
	public boolean logout(HttpServletResponse response){
		if(isAuthenticated()){
			Object sessionIdAttribute=WebContext.getAttribute(WebConstants.CURRENT_USER_SESSION_ID);
			UserInfo userInfo=WebContext.getUserInfo();
			Date logoutDateTime=new Date();
			
			_log.debug("Session " +WebContext.getAttribute(WebConstants.CURRENT_USER_SESSION_ID)+ ", user "+userInfo.getUsername()+" Logout, datetime "+DateUtils.toUtc(logoutDateTime)+" .");
		}
		return true;
		
	}
	
}
