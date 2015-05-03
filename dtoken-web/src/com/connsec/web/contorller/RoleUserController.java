package com.connsec.web.contorller;

import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.constants.OPERATEMESSAGE;
import com.connsec.dao.service.NavigationsService;
import com.connsec.dao.service.RoleUserService;
import com.connsec.domain.RoleUser;
import com.connsec.domain.UserInfo;
import com.connsec.web.WebContext;
import com.connsec.web.component.Grid;
import com.connsec.web.message.Message;
import com.connsec.web.message.MessageType;

/**
 * 系统角色操作管理
 * @author Crystal.Sea
 *
 */
@Controller
@RequestMapping(value = { "/roleUser" })
public class RoleUserController {
	final static Logger log = Logger.getLogger(RoleUserController.class);
	
	@Autowired
	@Qualifier("roleUserService")
	RoleUserService roleUserService;

	@Autowired
	@Qualifier("navigationsService")
	NavigationsService navigationsService;
	
	

	@RequestMapping(value={"/list"})
	public ModelAndView groupsList(){
		return new ModelAndView("roleuser/roleUserInfoList");
	}
	
	/**
	 * 根据角色获取角色用户
	 * @param roleUser
	 * @return
	 */
	@RequestMapping(value = { "/roleUserByRoleGrid" })
	@ResponseBody
	public Grid<UserInfo> roleUserByRoleGrid(@ModelAttribute("roleUser") RoleUser roleUser) {
		//roleUser.setRoleId(roleUser.getId());
		if(roleUser.getRoleId().equals("ORDINARY_USER")){
			return roleUserService.gridAllUserInfoInRole(roleUser);
		}else{
			return roleUserService.gridUserInfoInRole(roleUser);
		}
		
	}
	
	
	/**
	 * 获取不在角色的角色用户
	 * @param roleUser
	 * @return
	 */
	@RequestMapping(value = { "/roleUserNotInRoleGrid" })
	@ResponseBody
	public Grid<UserInfo> roleUserNotInRoleGrid(@ModelAttribute("roleUser") RoleUser roleUser) {
		return roleUserService.gridUserInfoNotInRole(roleUser);
	}


	@RequestMapping(value = { "/roleUserInfoList/{roleId}" })
	public ModelAndView roleUserInfoList(@PathVariable("roleId") String roleId) {
		log.debug("roleUserInfoList roleId : "+roleId);
		return new ModelAndView("roles/roleUserInfoList");
	}
	

	
	/**
	 * 删除角色用户
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/deleteRoleUser/{roleId}/{userId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteRoleUser(@PathVariable("roleId") String roleId,@PathVariable("userId") String userId) {
		//
		RoleUser roleUser=new RoleUser(roleId,userId);
		log.debug("deleteRoleUser roleUser : "+roleUser);
		if(roleUserService.delete(roleUser)){
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.info);
		}else{
			return  new Message((OPERATEMESSAGE.DELETE_ERROR),MessageType.error);
		}
	}



	/**
	 * 增加角色用户管理关系
	 * @param roleUser
	 * @return
	 */
	@RequestMapping(value = "insertRoleUser")
	@ResponseBody
	public Message insertRoleUser(@ModelAttribute("roleUser") RoleUser roleUser) {
		if (roleUser == null || roleUser.getRoleId() == null) {
			return  new Message("传入参数为空",MessageType.error);
		}
		String roleId = roleUser.getRoleId();
		
		
		boolean result = true;
		String userIds = roleUser.getUid();
		if (userIds != null) {
			String[] arrUserIds = userIds.split(",");
			
			for (int i = 0; i < arrUserIds.length; i++) {
				RoleUser newRoleUser = new RoleUser(roleId, arrUserIds[i]);
				newRoleUser.genId();
				result = roleUserService.insert(newRoleUser);
			}
			if(!result) {
				return  new Message((OPERATEMESSAGE.INSERT_ERROR),MessageType.error);
			}
			
		}
		return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.info);
	}
	
	@RequestMapping(value={"/addRoleUsersList"})
	public ModelAndView addRoleUsersList(){
		return new ModelAndView("roleuser/addRoleUsersList");
	}
	
	  @InitBinder  
	  public void initBinder(WebDataBinder binder) {  
	      binder.setIgnoreInvalidFields(true);  

	      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	      binder.registerCustomEditor(Date.class, "createdate",  
	              new CustomDateEditor(format, true));  
	  } 
	  
}
