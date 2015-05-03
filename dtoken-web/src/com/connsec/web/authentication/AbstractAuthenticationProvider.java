package com.connsec.web.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.connsec.config.ApplicationConfig;
import com.connsec.constants.LOGINTYPE;
import com.connsec.domain.UserInfo;
import com.connsec.otp.AbstractOTPAuthn;
import com.connsec.web.WebContext;
import com.connsec.web.WebConstants;
import com.connsec.web.authentication.realm.AbstractAuthenticationRealm;


/**
 * login Authentication abstract class
 * 
 * @author Crystal.Sea
 *
 */
public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {
	
    private static final Logger log = LoggerFactory.getLogger(AbstractAuthenticationProvider.class);

    @Autowired 
  	@Qualifier("applicationConfig")
  	protected ApplicationConfig applicationConfig;
    
    @Autowired 
  	@Qualifier("authenticationRealm")
	protected AbstractAuthenticationRealm authenticationRealm;
    
    @Autowired 
  	@Qualifier("tfaOTPAuthn")
    protected AbstractOTPAuthn tfaOTPAuthn;

	protected abstract String getProviderName();
    
    protected abstract Authentication doInternalAuthenticate(Authentication authentication);
    
    @SuppressWarnings("rawtypes")
	public boolean supports(Class authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    /* 
     * authenticate
     * (non-Javadoc)
     * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        log.debug("Trying to authenticate user '{}' via {}", username, getProviderName());
   
        try {
            authentication = doInternalAuthenticate(authentication);
        } catch (AuthenticationException e) {
        	e.printStackTrace();
            log.debug("Failed to authenticate user {} via {}: {}", new Object[]{username, getProviderName(), e.getMessage()});
            throw e;
        } catch (Exception e) {
        	e.printStackTrace();
            String message = "Unexpected exception in " + getProviderName() + " authentication:";
            log.error(message, e);
            throw new AuthenticationServiceException(message, e);
        }
        if (!authentication.isAuthenticated()) {
            return authentication;
        }
        
        // user authenticated
        log.debug("'{}' authenticated successfully by {}.", username, getProviderName());
        
        UserInfo userInfo=WebContext.getUserInfo();
        WebContext.setAttribute(WebConstants.CURRENT_USER_SESSION_ID, WebContext.getSession().getId());
        authenticationRealm.insertLoginHistory(userInfo,LOGINTYPE.LOCAL,"","xe00000004","success");
        
        // create new authentication response containing the user and it's authorities
        UsernamePasswordAuthenticationToken simpleUserAuthentication = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), authentication.getCredentials(), authentication.getAuthorities());
        return simpleUserAuthentication;
    }
    

    
    /**
     * session validate
     * @param j_username
     * @param j_cname
     * @param sessionId
     */
    protected void sessionValid(String j_sessionId){
    	if(j_sessionId==null || ! j_sessionId.equals(WebContext.getSession().getId())){
        	String message="login session valid error.";
        	log.debug("login session valid error.");
        	throw new BadCredentialsException(message);
        }
    }

    
    /**
     * login user by j_username and j_cname
     * first query user by j_cname 
     * if first step userinfo is null,query user from system
     * @param j_username
     * @param j_cname
     * @return
     */
    protected UserInfo loadUserInfo(String j_username,String j_password){
    	UserInfo userInfo = authenticationRealm.loadUserInfo(j_username,j_password);
    	
    	if(userInfo != null){
    		if(userInfo.getUserType()=="SYSTEM"){
   			 	log.debug("SYSTEM User Login. ");
    		}else{
    			log.debug("User Login. ");
    		}
    	}

    	return userInfo;
    }
    
    
    /**
     * check input   password empty 
     * @param password
     * @return
     */
    protected boolean emptyPasswordValid(String j_password){
		if(null==j_password||"".equals(j_password)){
			throw new BadCredentialsException("password is empty.");
    	}
    	return true;
    }
    
    /**
     * check input username or password empty 
     * @param j_username
     * @param password
     * @return
     */
    protected boolean emptyEmailValid(String j_email){
		if(null==j_email||"".equals(j_email)){
			throw new BadCredentialsException("email is empty.");
    	}
    	return true;
    }
    
    /**
     * check input username empty 
     * @param j_username
     * @return
     */
    protected boolean emptyUsernameValid(String j_username){
		if(null==j_username||"".equals(j_username)){
			throw new BadCredentialsException("j_username is empty.");
    	}
    	return true;
    }
    
    protected boolean userinfoValid(UserInfo userInfo,String j_username){
		if(null==userInfo){
			String message="login user  "+j_username+" not in this System .";
			log.debug("login user  "+j_username+" not in this System ."+message);
			UserInfo loginUser=new UserInfo(j_username);
			loginUser.genId();
			loginUser.setDisplayName("not exist");
			authenticationRealm.insertLoginHistory(loginUser,LOGINTYPE.LOCAL,"","xe00000004","user not exist");
			throw new BadCredentialsException("login user  "+j_username+" not in this System .");
    	}
    	return true;
    }
    
    
}