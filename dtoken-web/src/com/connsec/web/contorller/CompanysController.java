package com.connsec.web.contorller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.constants.OPERATEMESSAGE;
import com.connsec.dao.service.CompanysService;
import com.connsec.domain.Companys;
import com.connsec.web.WebContext;
import com.connsec.web.component.Grid;
import com.connsec.web.message.Message;
import com.connsec.web.message.MessageType;

/**
 * 菜单操作管理
 * 
 * @author Crystal.sea
 *
 */
@Controller
@RequestMapping(value={"/company"})
public class CompanysController {
	final static Logger log = Logger.getLogger(CompanysController.class);
	
	@Autowired
	@Qualifier("companysService")
	CompanysService companysService;

	
	@RequestMapping(value={"/list"})
	public ModelAndView companysList(){
		return new ModelAndView("company/companysList");
	}
	
	
	@RequestMapping(value = { "/grid" })
	@ResponseBody
	public Grid<Companys> queryDataGrid(@ModelAttribute("companys") Companys companys) {
		return companysService.grid(companys);
	}
	
	@RequestMapping(value = { "/forwardAdd" })
	public ModelAndView forwardAdd() {
		return new ModelAndView("company/companyAdd");
	}
	
	@RequestMapping(value = { "/forwardTenantCompany" })
	public ModelAndView forwardTenantCompany() {
		ModelAndView mav=new ModelAndView("company/tenantCompany");
		Companys company=new Companys();
		company.setId(WebContext.getUserInfo().getTid());
		company=companysService.load(company);
		mav.addObject("model", company);
		return mav;
	}
	
	/**
	 * 新增
	 * @param company
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/add"})
	public Message insert(@ModelAttribute("company") Companys company) {
		log.debug("-Add  :" + company);
		if (companysService.insert(company)) {
			return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.error);
		}
		
	}
	
	/**
	 * 查询
	 * @param company
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/query"}) 
	public Message query(@ModelAttribute("company") Companys company) {
		log.debug("-query  :" + company);
		if (companysService.load(company)!=null) {
			return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.INSERT_ERROR),MessageType.error);
		}
		
	}
	
	/**
	 * 修改
	 * @param company
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/update"})  
	public Message update(@ModelAttribute("company") Companys company) {
		log.debug("-update  company :" + company);
		if (companysService.update(company)) {
			return  new Message((OPERATEMESSAGE.UPDATE_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.UPDATE_ERROR),MessageType.error);
		}
		
	}
	
	/**
	 * 删除
	 * @param company
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/delete"})
	public Message delete(@ModelAttribute("company") Companys company) {
		log.debug("-delete  company :" + company);
		if (companysService.delete(company)) {
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.error);
		}
		
	}
	
	
}
