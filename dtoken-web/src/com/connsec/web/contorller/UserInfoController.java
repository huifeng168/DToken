package com.connsec.web.contorller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.dao.service.UserInfoService;
import com.connsec.domain.UserInfo;
import com.connsec.util.StringUtils;
import com.connsec.web.component.Grid;


/**
 * @author Crystal.Sea
 *
 */
@Controller
@RequestMapping(value = { "/userinfo" })
public class UserInfoController {
	final static Logger log = Logger.getLogger(UserInfoController.class);
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;

	
	/**
	 * 查询用户列表
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value={"/grid"})
	@ResponseBody
	public Grid<UserInfo> forwardUserInfoList(@ModelAttribute("userinfo") UserInfo userinfo){
		return userInfoService.grid(userinfo);
		
	}
	
	@RequestMapping(value={"/list"})
	public ModelAndView userInfoList(){
		return new ModelAndView("userinfo/userInfoList");
	}

	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
		    @Override
			public void setAsText(String value) {
		        	if(StringUtils.isNullOrBlank(value)){
		        		setValue(null);
		        	}else{
		        		setValue(value);
		        	}
		    }

		    
		});
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);  
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
