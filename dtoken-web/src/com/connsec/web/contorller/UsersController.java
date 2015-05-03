package com.connsec.web.contorller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.constants.OPERATEMESSAGE;
import com.connsec.crypto.ReciprocalUtils;
import com.connsec.dao.service.OrganizationsService;
import com.connsec.dao.service.UserInfoService;
import com.connsec.domain.Organizations;
import com.connsec.domain.UserInfo;
import com.connsec.util.StringUtils;
import com.connsec.web.WebContext;
import com.connsec.web.component.Grid;
import com.connsec.web.message.Message;
import com.connsec.web.message.MessageScope;
import com.connsec.web.message.MessageType;
import com.connsec.web.message.OperateType;


/**
 * @author Crystal.Sea
 *
 */
@Controller
@RequestMapping(value = { "/users" })
public class UsersController {
	final static Logger log = Logger.getLogger(UsersController.class);
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;
	
	@Autowired
	OrganizationsService organizationsService;
	
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	
	
	/**
	 * 查询用户列表
	 * @param user
	 * @return
	 */
	@RequestMapping(value={"/grid"})
	@ResponseBody
	public Grid<UserInfo> forwardUsersList(@ModelAttribute("userInfo") UserInfo userInfo){
		return userInfoService.grid(userInfo);
		
	}
	
	@RequestMapping(value={"/forwardAdd"})
	public ModelAndView forwardAddUsers(){
		ModelAndView modelAndView=new ModelAndView("users/userAdd");
		return modelAndView;
	}
	
	@RequestMapping(value={"/forwardAdd/{deptId}"})
	public ModelAndView forwardAddUsersWithDeptId(@PathVariable("deptId") String deptId){
		ModelAndView modelAndView=new ModelAndView("users/userAdd");
		Organizations organization=organizationsService.query(new Organizations(deptId)).get(0);
		UserInfo userInfo=new UserInfo();
		userInfo.setDepartment(organization.getName());
		userInfo.setDepartmentId(organization.getId());
		modelAndView.addObject("model", userInfo);
		return modelAndView;
	}
	
	
	@RequestMapping(value={"/list"})
	public ModelAndView usersList(){
		return new ModelAndView("users/usersList");
	}
	
	@RequestMapping(value={"/usersSelect/{uid}/{username}"})
	public ModelAndView usersSelect(
			@PathVariable("uid") String uid,
			@PathVariable("username") String username){
		ModelAndView modelAndView= new ModelAndView("users/usersSelect");
		modelAndView.addObject("uid", uid);
		modelAndView.addObject("username", username);
		return modelAndView;
	}
	
	/**
	 * 新增
	 * @param userInfo
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/add") 
	public ModelAndView addUsers(@Valid  @ModelAttribute("userInfo")UserInfo userInfo,BindingResult result) {
		log.debug(userInfo.toString());
		if(result.hasErrors()){
			 new Message("",result);
		}

		userInfo.genId();
		userInfo.setNameZHShortSpell(StringUtils.hanYu2Pinyin(userInfo.getDisplayName(), true));
		String password = passwordEncoder.encodePassword(userInfo.getPassword(), userInfo.getUsername());

		userInfo.setPassword(password);
		if( userInfoService.insert(userInfo)) {
			  new Message((OPERATEMESSAGE.INSERT_SUCCESS),userInfo,MessageType.success,OperateType.add,MessageScope.DB);
		}
		
		 new Message((OPERATEMESSAGE.INSERT_ERROR),MessageType.error);
		return   WebContext.forward("forwardUpdate/"+userInfo.getId());
	}
	
	@RequestMapping(value={"/forwardUpdate/{id}"})
	public ModelAndView forwardUpdateUsers(@PathVariable("id")String id){
		ModelAndView modelAndView=new ModelAndView("users/userUpdate");
		UserInfo userInfo=new UserInfo();
		userInfo.setId(id);
		userInfo=userInfoService.load(userInfo);
	
	modelAndView.addObject("model", userInfo);
		return modelAndView;
	}

	/**
	 * 查询用户，根据id
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getUsers/{id}") 
	public UserInfo getUserInfo(@PathVariable("id")String id) {
		log.debug(id);
		UserInfo userInfo = userInfoService.get(id);
	
		return userInfo;
	}
	
	/**
	 * 修改用户
	 * @param userInfo
	 * @param result
	 * @return
	 */

	@RequestMapping(value="/update") 
	public ModelAndView updateUsers(@Valid  @ModelAttribute("userInfo")UserInfo userInfo,BindingResult result) {
		log.debug(userInfo.toString());
		if(result.hasErrors()){
			 new Message("",result);
		}
		userInfo.setNameZHShortSpell(StringUtils.hanYu2Pinyin(userInfo.getDisplayName(), true));

		if(userInfoService.update(userInfo)) {
			new Message((OPERATEMESSAGE.UPDATE_SUCCESS),userInfo,MessageType.success,OperateType.add,MessageScope.DB);
			
		}
	    new Message((OPERATEMESSAGE.UPDATE_ERROR),MessageType.error);
		return   WebContext.forward("forwardUpdate/"+userInfo.getId());
	}
	
	
	/**
	 * 批量删除用户
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/batchDelete")  
	public Message batchDeleteUsers(@RequestParam("id")String id) {
		log.debug(id);
		if(userInfoService.logisticBatchDelete(StringUtils.string2List(id, ","))) {
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.DELETE_ERROR),MessageType.error);
		}
	}
	
	/**
	 * 根据用户id删除用户
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)  
	public Message deleteUsersById(@RequestParam("id") String id) {
		log.debug(id);
		if(userInfoService.logisticBatchDelete(StringUtils.string2List(id, ","))) {
			//provisioningPrepare.prepare(userInfo, OPERATEACTION.DELETE_ACTION);
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.success);
		} else {
			return  new Message((OPERATEMESSAGE.DELETE_ERROR),MessageType.error);
		}
	}
	

	
	@RequestMapping(value={"/forwardChangePassword/{id}"})
	public ModelAndView forwardChangePassword(@PathVariable("id")String id){
		ModelAndView modelAndView=new ModelAndView("users/changePassword");
		UserInfo userInfo=new UserInfo();
		userInfo.setId(id);
		userInfo=userInfoService.load(userInfo);
		
		modelAndView.addObject("model", userInfo);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/changePassword")  
	public Message changePassword( @ModelAttribute("userInfo")UserInfo userInfo) {
		log.debug(userInfo.getId());
		if(userInfoService.changePassword(userInfo)) {
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.DELETE_ERROR),MessageType.error);
		}
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
