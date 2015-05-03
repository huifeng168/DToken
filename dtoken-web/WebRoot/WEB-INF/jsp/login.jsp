<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page   language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page   import="org.springframework.security.core.AuthenticationException" %>
<%@ page   import="org.springframework.security.web.WebAttributes" %>
<%@ page   import="com.connsec.web.WebContext"%>
<%@ taglib prefix="authz"   	uri="http://www.springframework.org/security/tags"  %>
<%@ taglib prefix="c"       	uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn"     	 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring"  	uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" 			uri="http://www.connsec.com/tags" %> 
<%
 	if(WebContext.getUserInfo() != null) {
 %>
<script type="text/javascript"> 
	top.location="<s:Base />/index";
</script>
<%}%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title><spring:message  code="global.application"/></title>
<link rel="shortcut icon" type="image/x-icon" href="<s:Base />/images/favicon.ico"/>
<base href="<s:BasePath/>"/>

<link type="text/css" rel="stylesheet" href="<s:Base/>/css/blitzer/jquery-ui-1.10.2.custom.min.css"/>
<link type="text/css" rel="stylesheet" href="<s:Base/>/css/base.css"/>
<link type="text/css" rel="stylesheet" href="<s:Base/>/css/login.css"/>

<script type="text/javascript" src="<s:Base/>/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<s:Base/>/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="<s:Base/>/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<s:Base/>/jquery/platform.login.js"></script>
 
  
<script type="text/javascript"> 
$(function(){
	<%if (session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null && !(session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) instanceof AuthenticationException)) { %>
		$.alert({content:"<%= ((AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)).getMessage() %>"});
		<c:remove scope="session" var="AUTHENTICATION_EXCEPTION"/>
	<%}%>
});
</script>

</head>
<body >
<div class="container" >
		<table border="0">
		<tr>
			<td width="630px">
				
			</td>
			<td>
	<table id="tableform">
		<tr>
			<td>
				<div id="div_Login" >
					<form id="loginForm" name="loginForm" action="<s:Base />/login.do" method="post">
						<input type="hidden" name="j_auth_type" value="common"/>
						<table width="358px">
							<tr>
								<td colspan="2" style="height:36px;background-color: rgb(248, 248, 248);border-bottom: 1px solid rgb(227, 227, 227);font-size: 14px;font-weight: bold;"><spring:message  code="login.text.login.header"/></td>
							</tr>
							<tr>
								<td><spring:message  code="login.text.username"/>：</td>
								<td><input type='text' id='j_username' name='j_username' value="admin" tabindex="1"/></td>
								
							</tr>
							<tr>
								<td><spring:message  code="login.text.password"/>：</td>
								<td><input type='password' id='j_password'  name='j_password' value="admin"  tabindex="2" /></td>
								
							</tr>
							<c:if test="${'true'==isCaptcha}">
							<tr>
								<td><spring:message  code="login.text.captcha"/>：</td>
								<td><input type='text' id="j_captcha" name="j_captcha"  tabindex="3"  value="" /><img id="captchaimg" src="<c:url value="/captcha"/>" /></td>
								
							</tr>
							</c:if>
							<tr   style="display:none">
								<td>sessionid：</td>
								<td><input type='text' id="sessionid" name="j_sessionid" value="${sessionid}" /></td>
								
							</tr>
							<tr >
								<td colspan="2"><input id="loginSubmit" type="submit"  tabindex="4" class="button login_button"  value="<spring:message  code="login.button.login"/>"/></td>
								
							</tr>
							<tr>
								<td><spring:message  code="global.change.language"/>：</td>
								<td>
									<div > 
										 Locale : ${pageContext.response.locale}/<a href="<s:BasePath />/login?language=en">English</a>|<a href="<s:BasePath />/login?language=zh_CN">Chinese</a>
									</div>
								</td>
								
							</tr>
						</table>
						<div class="clear"></div>
					    </form>
					</div>
			</td>
			<td>
				
			</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
</div>		
</body>
</html>
