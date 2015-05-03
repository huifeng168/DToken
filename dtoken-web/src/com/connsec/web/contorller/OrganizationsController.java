package com.connsec.web.contorller;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.constants.OPERATEACTION;
import com.connsec.constants.OPERATEMESSAGE;
import com.connsec.dao.service.CompanysService;
import com.connsec.dao.service.OrganizationsService;
import com.connsec.domain.Companys;
import com.connsec.domain.Organizations;
import com.connsec.web.WebContext;
import com.connsec.web.component.TreeNode;
import com.connsec.web.component.TreeNodeList;
import com.connsec.web.message.Message;
import com.connsec.web.message.MessageType;

/**
 * 
 * 
 * @author Crystal.sea
 *
 */
@Controller
@RequestMapping(value={"/orgs"})
public class OrganizationsController {
	final static Logger log = Logger.getLogger(OrganizationsController.class);
	
	@Autowired
	OrganizationsService organizationsService;
	
	@Autowired
	CompanysService companysService;

	
	/**
	 * 查询所有菜单树
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/tree"})
	public List<HashMap<String,Object>> organizationsTree(@RequestParam(value = "id", required = false)String id){
		log.debug("organizationsTree id :"+id);
		Organizations org=new Organizations();
		Companys  company=companysService.load(null);
		org.setTid(company.getId());
		List<Organizations>   organizationsList=organizationsService.query(org);

		Organizations  rootOrganization=new Organizations();
		rootOrganization.setId("1");
		rootOrganization.setName(company.getShortName());
		rootOrganization.setFullName(company.getFullName());
		rootOrganization.setpName("ROOT");
		rootOrganization.setxPath("/1");
		rootOrganization.setpId("-1");
		
		TreeNodeList treeNodeList=new TreeNodeList();
		TreeNode rootTreeNode=new TreeNode("1",company.getShortName());
		rootTreeNode.setAttr("data", rootOrganization);
		rootTreeNode.setPId( "-1");
		rootTreeNode.setAttr("open", true);
		treeNodeList.addTreeNode(rootTreeNode.getAttr());
		
		for(Organizations  organization: organizationsList){
			TreeNode treeNode=new TreeNode(organization.getId(),organization.getName());
			if(organization.getHasChild()!=null&&organization.getHasChild().equals('Y')){
				treeNode.setHasChild();
			}
			
			//if(id.equals("-1"))treeNode.setAttr("open", true);
			treeNode.setAttr("data", organization);
			treeNode.setPId( organization.getpId());
			if(organization.getId().equals(company.getId())){
				treeNode.setAttr("open", true);
			}else{
				treeNode.setAttr("open", false);
			}
			treeNodeList.addTreeNode(treeNode.getAttr());
		}
		

		return treeNodeList.getTreeNodeList();
	}
	
	
	@RequestMapping(value={"/list"})
	public ModelAndView orgsTreeList(){
		return new ModelAndView("orgs/orgsList");
	}
	
	@RequestMapping(value={"/orgsSelect/{deptId}/{department}"})
	public ModelAndView orgsSelect(
				@PathVariable("deptId") String deptId,
				@PathVariable("department") String department){
		ModelAndView modelAndView=new ModelAndView("orgs/orgsSelect");
		modelAndView.addObject("deptId", deptId);
		modelAndView.addObject("department", department);
		return modelAndView;
	}
	
	
	/**
	 * 新增
	 * @param organization
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/add"})
	public Message insert(@ModelAttribute("organization") Organizations  organization) {
		log.debug("-Add  :" + organization);
		if(null==organization.getId()||organization.getId().equals("")){
			organization.genId();
		}
		
		if (organizationsService.insert(organization)) {
			return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.error);
		}
		
	}
	
	/**
	 * 查询
	 * @param organization
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/query"}) 
	public Message query(@ModelAttribute("organization") Organizations  organization) {
		log.debug("-query  :" + organization);
		if (organizationsService.load(organization)!=null) {
			return  new Message((OPERATEMESSAGE.INSERT_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.INSERT_ERROR),MessageType.error);
		}
		
	}
	
	/**
	 * 修改
	 * @param organization
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/update"})  
	public Message update(@ModelAttribute("organization") Organizations  organization) {
		log.debug("-update  organization :" + organization);
		if (organizationsService.update(organization)) {
			return  new Message((OPERATEMESSAGE.UPDATE_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.UPDATE_ERROR),MessageType.error);
		}
		
	}
	
	/**
	 * 删除
	 * @param organization
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/delete"})
	public Message delete(@ModelAttribute("organization") Organizations  organization) {
		log.debug("-delete  organization :" + organization);
		if (organizationsService.delete(organization)) {
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.success);
			
		} else {
			return  new Message((OPERATEMESSAGE.DELETE_SUCCESS),MessageType.error);
		}
		
	}
	
	@RequestMapping(value={"/orgUsersList"})
	public ModelAndView orgUsersList(){
		return new ModelAndView("orgs/orgUsersList");
	}
	
}
