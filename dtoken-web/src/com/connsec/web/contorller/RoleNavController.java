package com.connsec.web.contorller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.constants.OPERATEMESSAGE;
import com.connsec.dao.service.NavigationsService;
import com.connsec.dao.service.RoleNavService;
import com.connsec.domain.Navigations;
import com.connsec.domain.RoleNav;
import com.connsec.domain.Roles;
import com.connsec.web.WebContext;
import com.connsec.web.message.Message;
import com.connsec.web.message.MessageType;

/**
 * 系统角色操作管理
 * @author Crystal.Sea
 *
 */
@Controller
@RequestMapping(value = { "/roleNav" })
public class RoleNavController {
	final static Logger log = Logger.getLogger(RoleNavController.class);
	
	@Autowired
	@Qualifier("roleNavService")
	RoleNavService roleNavService;

	@Autowired
	@Qualifier("navigationsService")
	NavigationsService navigationsService;
	
	

	@RequestMapping(value = { "/rolesList" })
	public ModelAndView usersList() {
		return new ModelAndView("roles/rolesList");
	}


	

	
	@RequestMapping("/roleNavList")
	public ModelAndView rolemenuList() {
		ModelAndView mv = new ModelAndView();
		List<Navigations> navigationsList = navigationsService.query(new Navigations());
		mv.addObject("navigationsList", navigationsList);
		mv.setViewName("rolenav/roleNavList");

		return mv;
	}

	/**
	 * 根据角色获取菜单列表
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/queryNavs/{roleId}")
	@ResponseBody
	public List<Navigations> queryNavs(@PathVariable("roleId") String roleId) {
		List<Navigations> navs = roleNavService.queryNavs(roleId); 
		return navs;
	}
	


	/**
	 * 增加角色菜单关联关系
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "insert")
	@ResponseBody
	public Message insertRoleNav(@ModelAttribute("role") Roles role) {
		if (role == null || role.getId() == null) {
			return  new Message("传入参数为空",MessageType.error);
		}
		String roleId = role.getId();
		
		roleNavService.delete(roleId);
		
		boolean result = true;
		String munusId = role.getNavsId();
		if (munusId != null) {
			String[] arrMenusId = munusId.split(",");
			List<RoleNav> listRoleNav=new ArrayList<RoleNav>();
			for (int i = 0; i < arrMenusId.length; i++) {
				RoleNav roleMenu = new RoleNav(roleId, arrMenusId[i]);
				roleMenu.genId();
				listRoleNav.add(roleMenu);
			}
			result = roleNavService.insert(listRoleNav);
			if(!result) {
				return  new Message((OPERATEMESSAGE.INSERT_ERROR),MessageType.error);
			}
			
		}
		return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.info);
	}
	


	  @InitBinder  
	  public void initBinder(WebDataBinder binder) {  
	      binder.setIgnoreInvalidFields(true);  

	      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	      binder.registerCustomEditor(Date.class, "createdate",  
	              new CustomDateEditor(format, true));  
	  } 
	  
	
}
