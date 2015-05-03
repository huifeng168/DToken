<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page   language="java"  import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles"   uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="authz"   uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<authz:authorize ifNotGranted="ROLE_USER"><c:redirect url="/login"/></authz:authorize>

<html> 
	<head>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="css" />
		<tiles:insertAttribute name="js" />	 
	</head>
	<body> 
		<div id="top">
				<tiles:insertAttribute name="top" />
			</div>
		<div id="navs">
			<div id="nav_primary">
				<tiles:insertAttribute name="nav_primary" />
			</div>
			<div id="nav_second">
				<tiles:insertAttribute name="nav_second" />
			</div>
		</div>
		<div id="mainFrame">
			<div id="nav_third">
				<tiles:insertAttribute name="nav_third" />
			</div>
			<div id="content">
				<tiles:insertAttribute name="content" />
			</div>
			
		</div>
		<div id="footer">
				<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>
