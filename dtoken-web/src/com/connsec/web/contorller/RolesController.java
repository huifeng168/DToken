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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.constants.OPERATEMESSAGE;
import com.connsec.dao.service.NavigationsService;
import com.connsec.dao.service.RolesService;
import com.connsec.domain.Roles;
import com.connsec.util.StringUtils;
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
@RequestMapping(value = { "/roles" })
public class RolesController {
	final static Logger log = Logger.getLogger(RolesController.class);
	
	@Autowired
	@Qualifier("rolesService")
	RolesService rolesService;

	@Autowired
	@Qualifier("navigationsService")
	NavigationsService navigationsService;
	
	

	@RequestMapping(value = { "/list" })
	public ModelAndView usersList() {
		return new ModelAndView("roles/rolesList");
	}
	
	/**
	 * 获取角色列表
	 * @param role
	 * @return
	 */
	@RequestMapping(value = { "/grid" })
	@ResponseBody
	public Grid<Roles> queryDataGrid(@ModelAttribute("role") Roles role) {
		log.debug("role : "+role);
		role.setStatus(1);
		return rolesService.grid(role);
	}
	
	

	
	@RequestMapping(value = { "/forwardAdd" })
	public ModelAndView forwardAdd() {
		return new ModelAndView("roles/roleAdd");
	}
	
	/**
	 * 新增
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Message addRole(@ModelAttribute("role") Roles role) {
		role.genId();
		log.debug("addRole roleUser : "+role);
		if(rolesService.insert(role)){
			return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.success);
		}else{
			return  new Message((OPERATEMESSAGE.INSERT_ERROR),MessageType.error);
		}
	}
	
	@RequestMapping(value = { "/forwardUpdate/{roleId}" })
	public ModelAndView forwardUpdate(@PathVariable("roleId") String roleId) {
		Roles role=rolesService.get(roleId);
		return new ModelAndView("roles/roleUpdate","role",role);
	}

	/**
	 * 修改
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	@ResponseBody
	public Message updateRole(@ModelAttribute("role") Roles role) {
		log.debug("updateRole roleUser : "+role);
		if(rolesService.update(role)){
			return  new Message((OPERATEMESSAGE.UPDATE_SUCCESS),MessageType.success);
		}else{
			return  new Message((OPERATEMESSAGE.UPDATE_ERROR),MessageType.error);
		}
	}
	
	
	/**
	 * 删除
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = { "/delete" })
	public Message deleteRole(@RequestParam("id") String id) {
		log.debug("id	:"	+id);
		if(rolesService.logisticBatchDelete(StringUtils.string2List(id, ","))){
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.success);
		}else{
			return  new Message((OPERATEMESSAGE.DELETE_ERROR),MessageType.error);
		}
	}
	
	
	  @InitBinder  
	  public void initBinder(WebDataBinder binder) {  
	      binder.setIgnoreInvalidFields(true);  

	      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	      binder.registerCustomEditor(Date.class, "createdate",  
	              new CustomDateEditor(format, true));  
	  } 
	  
	 
}
