package com.connsec.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.connsec.dao.service.LogsService;
import com.connsec.domain.Companys;
import com.connsec.domain.Logs;
import com.connsec.domain.UserInfo;
import com.connsec.json.util.JsonUtils;
import com.connsec.web.WebContext;
import com.connsec.web.message.Message;
import com.connsec.web.message.MessageScope;

/**
 * Contorller调用完成后进行日志操作
 * 
 * 日志处理需在parasec-servlet.xml中配置
 * mvc:interceptors  log
 * @author Crystal.Sea
 *
 */
public class LogAdapter extends HandlerInterceptorAdapter {
	
	private static final Log log = LogFactory.getLog(LogAdapter.class);
	
	 @Autowired
	 @Qualifier("logsService")
	 private LogsService logsService;
	
	// after the handler is executed
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	
	}
}
