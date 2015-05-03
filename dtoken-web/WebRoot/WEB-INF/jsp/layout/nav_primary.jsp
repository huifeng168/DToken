<%@ page   language="java"     import="java.util.*"   pageEncoding="UTF-8"%>
<%@ page   language="java"     import="com.connsec.web.*" %>
<%@ page   language="java"     import="com.connsec.domain.*"%>
<%@ page   language="java"     import="com.connsec.domain.userinfo.*"%>
<%@ taglib prefix="s"  uri="http://www.connsec.com/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="nav_primary">
				
<div id="nav_primay_content" class="menuprimary">
	<ul >
<li  id='nav_primay_1001' class='nav_primay_level primaryleft'  xpath='/10/1001'><a  href='/dtoken/users/list?mnid=1001'>用户管理</a>
	<div id='nav_child_1001' class='nav_second_child'>
		<ul></ul></div>
</li>
<li  id='nav_primay_1002' class='nav_primay_level'  xpath='/10/1002'><a  href='/dtoken/orgs/list?mnid=1002'>组织管理</a>
	<div id='nav_child_1002' class='nav_second_child'>
		<ul></ul></div>
</li>
<li  id='nav_primay_1003' class='nav_primay_level'  xpath='/10/1003'><a  href='/dtoken/logs/loginHistoryList?mnid=1003'>历史审计</a>
	<div id='nav_child_1003' class='nav_second_child'>
		<ul>
			<li id='nav_second_100301' ' class='nav_second_level'  xpath='/1003/100301'><a href='/dtoken/logs/loginHistoryList?mnid=1003'>登录日志</a></li>
			<li id='nav_second_100302' ' class='nav_second_level'  xpath='/1003/100302'><a href='/dtoken/logs/list?mnid=1003'>操作日志</a></li></ul></div>
</li>
<li  id='nav_primay_1004' class='nav_primay_level'  xpath='/10/1004'><a  href='/dtoken/otptokencofing/configtype?mnid=1004'>令牌类型</a>
	<div id='nav_child_1004' class='nav_second_child'>
		<ul></ul></div>
</li>
<li  id='nav_primay_1005' class='nav_primay_level'  xpath='/10/1005'><a  href='/dtoken/otptokencofing/config?mnid=1005'>令牌参数配置</a>
	<div id='nav_child_1005' class='nav_second_child'>
		<ul></ul></div>
</li>
<li  id='nav_primay_1006' class='nav_primay_level'  xpath='/10/1006'><a  href='/dtoken/company/forwardTenantCompany?mnid=1006'>系统管理</a>
	<div id='nav_child_1006' class='nav_second_child'>
		<ul>
			
			<li id='nav_second_100601' ' class='nav_second_level'  xpath='/1006/100601'><a href='/dtoken/company/forwardTenantCompany?mnid=1006'>企业管理</a></li>
			<li id='nav_second_100602' ' class='nav_second_level'  xpath='/1006/100602'><a href='/dtoken/roles/list?mnid=1006'>角色管理</a></li>
			<li id='nav_second_100603' ' class='nav_second_level'  xpath='/1006/100603'><a href='/dtoken/roleUser/list?mnid=1006'>角色用户管理</a></li></ul></div>
</li>
	</ul>
	<br style="clear: left" />
</div>
<script>
	$(function(){
		function displaySecondNavs(menuId){
			$("#nav_second").html("<div class='menusecond'>"+$("#"+menuId+" .nav_second_child").html()+"</div><br style='clear: left' />");
		}
		$("#nav_primay_content ul li").mouseover(function(){
			displaySecondNavs(this.id);
		});
		displaySecondNavs("nav_primay_<%=request.getParameter("mnid")%>");
	});
</script>
</div>