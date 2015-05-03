package com.connsec.web.contorller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.connsec.dao.service.LoginHistoryService;
import com.connsec.dao.service.LogsService;
import com.connsec.domain.LoginHistory;
import com.connsec.domain.Logs;
import com.connsec.util.DateUtils;
import com.connsec.web.component.Grid;

/**
 * 登录日志和操作日志查询
 * 
 * @author Crystal.sea
 *
 */

@Controller
@RequestMapping(value={"/logs"})
public class LogsController {
final static Logger log = Logger.getLogger(LogsController.class);
	
	@Autowired
	LoginHistoryService loginHistoryService;
	
	@Autowired
	LogsService logsService;
	
	/**
	 * 查询操作日志
	 * @param logs
	 * @return
	 */
	@RequestMapping(value={"/grid"})
	@ResponseBody
	public Grid<Logs> logsDataGrid(@ModelAttribute("logs") Logs logs){
		log.debug("logs/datagrid/ logsGrid() "+logs);
		return logsService.grid(logs);
	}
	
	
	@RequestMapping(value={"/list"})
	public String List(){
		return "logs/logsList";
	}
	
	@RequestMapping(value={"/loginHistoryList"})
	public String loginHistoryList(){
		return "logs/loginHistoryList";
	}
	
	/**
	 * @param LoginHistory
	 * @return
	 */
	@RequestMapping(value={"/loginHistory/grid"})
	@ResponseBody
	public Grid<LoginHistory> logAuthsGrid(@ModelAttribute("loginHistory") LoginHistory loginHistory){
		log.debug("logs/loginHistory/datagrid/ logsGrid() "+loginHistory);
		return loginHistoryService.grid(loginHistory);
	}
	

	@RequestMapping(value={"/loginAppsHistoryList"})
	public String loginAppsHistoryList(){
		return "logs/loginAppsHistoryList";
	}
	

	
	
	

	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.FORMAT_DATE_HH_MM_SS);
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
