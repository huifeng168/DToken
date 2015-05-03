<%@ page 	language="java"   import="java.util.*" 	pageEncoding="UTF-8"%>
<%@ taglib 	prefix="spring"   uri="http://www.springframework.org/tags" %>
<%@ taglib 	prefix="s" uri="http://www.connsec.com/tags" %>
<%@ taglib 	prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="c"		  uri="http://java.sun.com/jsp/jstl/core"%>


		<script type="text/javascript">	
			
			
			$(function () {
				$("#addRoleBtn").on("click",function(){
					$.window({url:$(this).attr("url"),width:410,height:230,close:$.windowGridRefresh});
				});
				
				$(".updateRoleBtn").on("click",function(){
					$.window({url:$(this).attr("url"),width:410,height:230,close:$.windowGridRefresh});
				});
				
				$(".newRoleUserInfoBtn").on("click",function(){
					$.window({url:$(this).attr("url"),width:600,height:300,close:$.windowSubGridRefresh});
				});
				
				
				$(".deleteRoleUserBtn").on("click",function(){
					var _this=this;
					$.conform({
					    content		:	$.platform.messages.submit.conformText,
					    callback	:	function () {
					    	$.post($(_this).attr("url"), {_method:"delete",currTime:(new Date()).getTime()}, function(data) {
					   			if("success"==data.messageType){
									$.alert({content:data.message,type:"succeed"});
								}else if("error"==data.messageType){
									$.alert({content:data.message,type:"error"});
								}
								
								$("#"+$(_this).attr("subgrid")).jqGrid('setGridParam').trigger("reloadGrid");
					   		});
					    }
					});
				});
			
			});
		</script>

		<div class="mainwrap" id="main">
			<s:Grid id="list" url="/groups/grid" multiselect="true" resize="true" >	
					<s:Column width="0" field="id" title="id" hidden="true"/>
					<s:Column width="250" field="shortName" title="company.shortname" />
					<s:Column width="250" field="fullName" title="company.fullname" />
					<s:Column width="0" field="createdBy" title="common.text.createdby" hidden="true"/>
					<s:Column width="0" field="createdDate" title="common.text.createddate" hidden="true"/>
					<s:Column width="0" field="modifiedBy" title="common.text.modifiedby" hidden="true"/>
					<s:Column width="0" field="modifiedDate" title="common.text.modifieddate" hidden="true"/>
				</s:Grid>
					
		</div>