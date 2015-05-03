package com.connsec.web.authentication;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.connsec.domain.UserInfo;
import com.connsec.web.WebContext;


/**
 * database Authentication provider
 * @author Crystal.Sea
 *
 */
public class RealmAuthenticationProvider extends AbstractAuthenticationProvider {
	
    private static final Logger log = LoggerFactory.getLogger(RealmAuthenticationProvider.class);

    protected String getProviderName() {
        return "UserInfo";
    }
    
    @Override
    protected Authentication doInternalAuthenticate(Authentication authentication) {
    	HttpSession session = WebContext.getSession();
        // All your user authentication needs
        String j_username = (String) authentication.getPrincipal();
        String j_password = (String) authentication.getCredentials();
        String j_sessionId=WebContext.getRequest().getParameter("j_sessionid");

		log.info("principal : "+j_username);
		log.info("credentials : PROTECTED");
        log.info("sessionId : "+j_sessionId);
    	log.info("session getId() : "+session.getId());
    	log.info("Authentication principal :"+authentication.getName()+" , credentials : ********");
 
    	sessionValid(j_sessionId);
    	
    	
    	emptyPasswordValid(j_password);
    	
    	UserInfo userInfo = null;
    	
		emptyUsernameValid(j_username);
		
		userInfo= loadUserInfo(j_username,j_password);
    	
    	userinfoValid(userInfo, j_username);
    	
    	authenticationRealm.passwordValid(userInfo, j_password);
    	/**
    	 *  put userInfo to current session context
    	 */
	    WebContext.setUserInfo(userInfo);
	    
	    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(
				userInfo,
				j_password,
				authenticationRealm.grantAuthorityAndNavs(userInfo));
	    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(WebContext.getRequest()));
	    
    	return usernamePasswordAuthenticationToken;
    }
}