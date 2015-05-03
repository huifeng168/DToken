package com.connsec.web.contorller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.web.WebContext;
import com.connsec.web.WebConstants;
import com.connsec.web.authentication.realm.AbstractAuthenticationRealm;

@Controller
public class LogoutController {
	
	private static Log _log = LogFactory.getLog(LogoutController.class);
	
	@Autowired
	@Qualifier("authenticationRealm")
	AbstractAuthenticationRealm authenticationRealm;
	
 	@RequestMapping(value={"/logout"})
 	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
 		ModelAndView modelAndView = new ModelAndView();
 		authenticationRealm.logout(response);
 		SavedRequest  firstSavedRequest = (SavedRequest)WebContext.getAttribute(WebConstants.FIRST_SAVED_REQUEST_PARAMETER);
 		String reLoginUrl=WebContext.getHttpContextPath()+"/login";
 		if(firstSavedRequest!=null){
 			reLoginUrl= firstSavedRequest.getRedirectUrl();
 		}
 		_log.debug("re Login URL : "+ reLoginUrl);
 		modelAndView.addObject("reloginUrl",reLoginUrl);
 		request.getSession().invalidate();
	 		
 		modelAndView.setViewName("loggedout");
		return modelAndView;
 	}
 	
}
