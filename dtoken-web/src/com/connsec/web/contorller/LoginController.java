package com.connsec.web.contorller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.config.ApplicationConfig;
import com.connsec.web.WebContext;
import com.connsec.web.WebConstants;


/**
 * @author Crystal.Sea
 *
 */
@Controller
public class LoginController {
	private static Log _log = LogFactory.getLog(LoginController.class);
	
	@Autowired
  	@Qualifier("applicationConfig")
  	protected ApplicationConfig applicationConfig;
 	
	
	
	
	/**
	 * init login
	 * @return
	 */
 	@RequestMapping(value={"/login"})
	public ModelAndView login(
			HttpServletRequest request,
			HttpServletResponse response, String remeberMe) {
		_log.debug("LoginController /login.");
		ModelAndView modelAndView = new ModelAndView();
		
		boolean isAuthenticated= WebContext.isAuthenticated();
	

		//for normal login
		if(!isAuthenticated){
			modelAndView.addObject("isRemeberMe", applicationConfig.getLoginConfig().isRemeberMe());
			
			modelAndView.addObject("isCaptcha", applicationConfig.getLoginConfig().isCaptcha());
			modelAndView.addObject("sessionid", WebContext.getSession().getId());
			
		}
		//save  first protected url 
		SavedRequest  firstSavedRequest = (SavedRequest)WebContext.getAttribute(WebConstants.FIRST_SAVED_REQUEST_PARAMETER);
		if(firstSavedRequest==null){
			RequestCache requestCache = new HttpSessionRequestCache();
			SavedRequest  savedRequest =requestCache.getRequest(request, response);
			if(savedRequest!=null){
				_log.debug("first request parameter "+savedRequest.getRedirectUrl());
				WebContext.setAttribute(WebConstants.FIRST_SAVED_REQUEST_PARAMETER, savedRequest);
			}
		}else {
			WebContext.setAttribute(WebConstants.SPRING_PROCESS_SAVED_REQUEST, firstSavedRequest);
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
