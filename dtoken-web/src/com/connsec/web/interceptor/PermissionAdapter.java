package com.connsec.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.connsec.config.ApplicationConfig;

/**
 * 权限Interceptor处理
 * 权限处理需在servlet.xml中配置
 *  mvc:interceptors  permission
 * @author Crystal.Sea
 *
 */

public class PermissionAdapter extends HandlerInterceptorAdapter {
	private static final Log log = LogFactory.getLog(PermissionAdapter.class);
	//无需Interceptor url
	@Autowired
	@Qualifier("applicationConfig")
	private ApplicationConfig applicationConfig;
	
	/*
	 * 请求前处理
	 *  (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
/*		UserInfo userInfo =Application.getUserInfo();//取得登录用户
		
		if(userInfo==null||Application.getRoles()==null){//判断用户和角色
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
			return false;
		}
		//取得当前访问地址 Access Url
		String accessUrl=request.getServletPath().substring(1);
		log.debug("access URL : "+accessUrl);
		//匹配authedMenuList
		String []allAccessUrl=applicationConfig.getAllAccessUrlArray();
		for(int i=0;i<allAccessUrl.length;i++){
			if(accessUrl.equals(allAccessUrl[i])){
				return true;
			}
		}
		
		boolean preHandler = super.preHandle(request, response, handler);
		
		if(preHandler) {
			preHandler = false;
			//菜单权限匹配
			List<Navigations> listMenus = Application.getMenus();
			for(Navigations menu : listMenus) {
				if(StringUtil.isNotEmpty(menu.getUrl())) {
					if(accessUrl.equals(menu.getUrl())) {//菜单url和Access URL全匹配
						preHandler = true;
					} else {//菜单url和Access URL参数匹配
						String[] accessUrlArray = accessUrl.split("/");
						String[] menuUrlArray = menu.getUrl().split("/");
						if(accessUrlArray.length == menuUrlArray.length && accessUrlArray[0].equals(menuUrlArray[0])) {
							for(int i = 0; i < accessUrlArray.length; i++) {
								if(accessUrlArray[i].equals(menuUrlArray[i])) {
									preHandler = true;
								} else if(menuUrlArray[i].indexOf("{") > -1 && menuUrlArray[i].indexOf("}") > -1) {
									preHandler = true;
								} else {
									preHandler = false;
									break;
								}
							}
						}
					}

					if(preHandler) {
						log.debug("menu name : " + menu.getName() + " , url "
								+ menu.getUrl() + " , accessible : "
								+ preHandler);
						break;
					}
				}
			}
			
			if(!preHandler){//无权限转向
				log.debug("You do not have permission to access "+accessUrl);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accessdeny");
				dispatcher.forward(request, response);
				return false;
			}
		}*/
		return true;
	}
}
