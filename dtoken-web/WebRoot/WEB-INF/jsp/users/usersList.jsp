<%@ page   contentType="text/html; charset=UTF-8" import="java.util.Map,java.util.LinkedHashMap" %>
<%@ page   import="com.connsec.web.*"%>
<%@ taglib prefix="c"		    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" 	uri="http://www.connsec.com/tags" %>
<%@ taglib prefix="spring"	    uri="http://www.springframework.org/tags" %>
<script type="text/javascript">	
	function beforeWindow(){
   		$("#changepwdBtn").attr("wurl","<s:Base/>/users/forwardChangePassword/"+$.gridSel("#list"));
   	}
   	
   	function genderFormatter(value, options, rData){
   		if(value==1){
   			return '<spring:message  code="userinfo.gender.female" />';
   		}else{
   			return '<spring:message  code="userinfo.gender.male" />';
   		}
	};
</script>
	<div id="tool_box">
		<table  class="datatable">
			<tr>
				<td  width="120px">
			 		 <spring:message  code="userinfo.username"/>:
				</td>
				<td  width="375px">
					<form id="basic_search_form">
				 			<input name="username" type="text" style ="width:150px">
				 			<input class="button primary"  id="searchBtn" type="button" size="50" value="<spring:message  code="button.text.search"/>">
				 			<input class="button"  id="advancedSearchExpandBtn" type="button" size="50"  value="<spring:message  code="button.text.expandsearch"/>" expandValue="<spring:message  code="button.text.expandsearch"/>"  collapseValue="<spring:message  code="button.text.collapsesearch"/>">
					 	</form>
				</td>
				<td colspan="2"> 
					 <div id="tool_box_right">
					 	<input class="button window" id="changepwdBtn" type="button" value="<spring:message  code="button.text.changepassword"/>" 
						 		    wurl="<s:Base/>/users/forwardChangePassword" wwidth="600px" wheight="250px" />
						 <input class="button" id="addBtn" type="button" value="<spring:message  code="button.text.add"/>" 
						 		    wurl="<s:Base/>/users/forwardAdd"
					 		    	target="forward"  ref="departmentId">
					 	<input class="button"  id="modifyBtn" type="button" value="<spring:message  code="button.text.edit"/>" 
					 				wurl="<s:Base/>/users/forwardUpdate"
					 		    	target="forward"> 
					 	<input class="button"  id="deleteBtn" type="button" value="<spring:message  code="button.text.delete"/>"
					 				wurl="<s:Base/>/users/delete" />
					</div>
				</td>
			</tr>
		</table>
 		
		
 	</div>
 	
 	<div id="advanced_search">
 		<form id="advanced_search_form">
 			<table   class="datatable">
	 			<tr>
	 				<td width="120px"><spring:message  code="userinfo.displayName"/></td>
		 			<td width="360px">
		 				<input name="displayName" type="text" >
		 			</td>
		 			<td width="120px"><spring:message  code="userinfo.department"/></td>
		 			<td width="360px">
			 			<input type="hidden" id="departmentId" name="departmentId"  title="" value=""/>
						<input style="width:70%"  type="text" id="department" name="department"  title="" value=""/>
			 			<s:Dialog text="button.text.select" title="department" url="/orgs/orgsSelect/deptId/department" width="300" height="400" />
			 		</td>
			 </tr>
			</table>
 		</form>
 	</div>
 	
		<s:Grid id="list" url="/users/grid" multiselect="false">	
				<s:Column width="0" field="id" title="id" hidden="true"/>
				<s:Column width="100" field="username" title="userinfo.username"/>
				<s:Column width="100" field="displayName" title="userinfo.displayName"/>
				<s:Column width="100" field="employeeNumber" title="userinfo.employeeNumber"/>
				<s:Column width="100" field="department" title="userinfo.department"/>				
				<s:Column width="50" field="gender" title="userinfo.gender" formatter="genderFormatter" />
			</s:Grid>