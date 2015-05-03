<%@ page    language="java"     import="java.util.*"   pageEncoding="UTF-8"%>
<%@ page    language="java"     import="com.connsec.web.*"%>
<%@ taglib  prefix="s"  uri="http://www.connsec.com/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<!-- javascript js begin  -->
	<!-- jquery base -->
	<script type="text/javascript" src="<s:Base/>/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<s:Base/>/jquery/jquery-ui-1.10.3.custom.min.js"></script>
	
	<!-- metadata -->
	<script type="text/javascript" src="<s:Base/>/jquery/jquery.metadata.js"></script>
	
	<!-- jqGrid-4.4.5-->
	<link type="text/css" rel="stylesheet" href="<s:Base/>/jquery/jqGrid-4.4.5/css/ui.jqgrid.css"/>
	<link type="text/css" rel="stylesheet" href="<s:Base/>/css/grid.css"/>
	<script type="text/javascript" src="<s:Base/>/jquery/jqGrid-4.4.5/js/i18n/grid.locale-<%=WebContext.getLocale().toString().substring(0, 2) %>.js"></script>
	<script type="text/javascript" src="<s:Base/>/jquery/jqGrid-4.4.5/js/jquery.jqGrid.src.js"></script>

	<!-- zTreev3.2 -->
	<link type="text/css" rel="stylesheet" href="<s:Base/>/jquery/zTree-v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
	<script type="text/javascript" src="<s:Base/>/jquery/zTree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
	 
	<!-- artDialog-5.0.4 -->
	<link type="text/css" rel="stylesheet" href="<s:Base/>/jquery/artDialog-5.0.4/skins/platform.css"  />
	<script type="text/javascript" src="<s:Base/>/jquery/artDialog-5.0.4/jquery.artDialog.min.js"></script>
	<script type="text/javascript" src="<s:Base/>/jquery/artDialog-5.0.4/artDialog.plugins.min.js"></script>

	<!-- selecter-2.1.4 -->
	<link type="text/css" rel="stylesheet" href="<s:Base/>/jquery/selecter-3.2.3/jquery.fs.selecter.css"  />
	<script type="text/javascript" src="<s:Base/>/jquery/selecter-3.2.3/jquery.fs.selecter.js"></script>

	<!-- DateTimePicker-2.4.1 -->
	<link type="text/css" rel="stylesheet" href="<s:Base/>/jquery/DateTimePicker-2.4.1/jquery.datetimepicker.css"  />
	<script type="text/javascript" src="<s:Base/>/jquery/DateTimePicker-2.4.1/jquery.datetimepicker.js"></script>

	<link type="text/css" rel="stylesheet" href="<s:Base/>/jquery/switchtab/switchtab.css"/>
	<script type="text/javascript" src="<s:Base/>/jquery/switchtab/switchtab.js"></script>

	<!-- form -->
	<script type="text/javascript" src="<s:Base/>/jquery/jquery.form.js"></script>
	
	<!-- form -->
	<script type="text/javascript" src="<s:Base/>/jquery/json2form/json2form.js"></script>
	
	<!-- blockUI -->
	<script type="text/javascript" src="<s:Base/>/jquery/jquery.blockUI.js"></script>
	
	<!-- serializeObject -->
	<script type="text/javascript" src="<s:Base/>/jquery/jquery.serialize-object.min.js"></script>

	<!-- validation -->
	<script type="text/javascript" src="<s:Base/>/jquery/jquery-validation-1.11.1/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<s:Base/>/jquery/jquery-validation-1.11.1/localization/messages_<%=WebContext.getLocale().toString().substring(0, 2) %>.js"></script>
	 
	<!-- common script start -->
	<script type="text/javascript">
		$(function () {
			$(".datetimepicker").datetimepicker({format:'Y-m-d H:i',lang:'<spring:message />'});
			
			$(".datepicker").datetimepicker({timepicker:false,format:'Y-m-d',lang:'<spring:message />'});
			
			$.platform = $.platform || {};
			$.platform.messages = $.platform.messages || {};
			$.extend($.platform.messages, {
				window:	{
					title		:	'<spring:message  code="common.window.title" />'
				},
				alert:	{
					title		:	'<spring:message  code="common.alert.title" />',
					closeText	:	'<spring:message  code="common.alert.closeText" />'
				},
				conform:{
					title		:	'<spring:message  code="common.conform.title" />',
					yes			:	'<spring:message  code="common.conform.yes" />',
					no			:	'<spring:message  code="common.conform.no" />'
				},
				select:{
					alertText	: 	'<spring:message  code="common.select.hintText" />'
				},
				del:{
					conformText	: 	'<spring:message  code="common.delete.hintText" />'
				},
				grid:{
					loadtext	:	'<spring:message  code="common.grid.loadtext" />',
					loadnodata	:	'<spring:message  code="common.grid.loadnodata" />'
				},
				submit:{
					conformText	:	'<spring:message  code="common.submit.hintText" />',
					errorText	:	'<spring:message  code="common.submit.errorText" />'
				}
			});
		});
	
	</script>
	<!-- common script end -->
	
	<!-- platform common script -->	
	<script type="text/javascript" src="<s:Base/>/jquery/platform.common.js"></script>
	<!-- common js end  -->