<%@ page 	contentType="text/html; charset=UTF-8" 	import="java.util.Map,java.util.LinkedHashMap" %>
<%@ taglib  prefix="s"  uri="http://www.connsec.com/tags" %>
<%@ taglib  prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix="c"		   uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript">
		
		function onSelectRow(id){
			
	   		//$("#groupId").val(id);
	   		$("#list2").setGridParam({ postData: { roleId: id} });
	   		$("#list2").trigger('reloadGrid', [{page:1}]);
	   	}
	
		function afterSubmit(data){
			$("#list2").trigger('reloadGrid');
		}
		
		$(function () {
			$("#roleUsersSaveBtn").click(function(){
				var selectIds = $("#list").jqGrid("getGridParam", "selarrrow");
				if(selectIds == null || selectIds == "") {
					$.alert({content:$.platform.messages.select.alertText});
					return false;
				}
				$("#uid").val(selectIds);
				$("#submitBtn").click();
				//$("#uid").val($("#list").jqGrid("getGridParam", "selarrrow"));
			});
		});
	</script>

	<div id="tool_box">
	 		<table   class="datatable">
 				<tr>
		 			<td width="120px"><spring:message  code="userinfo.username"/>:</td>
		 			<td width="374px">
		 				<form id="basic_search_form">
				 			<input type="text" name="username" style ="width:150px">
				 			<input class="button primary"  id="searchBtn" type="button" size="50" value="<spring:message  code="button.text.search"/>">
				 		</form>
		 			</td>
				 	<td colspan="2"> <div id="tool_box_right">
							<input class="button window" id="addRoleUsersBtn" type="button" value="<spring:message  code="button.text.add.member"/>" 
			 		    		wurl="<s:Base/>/roleUser/addRoleUsersList" wwidth="800px" wheight="550px" />
				 	</div>
				 	</td>
				</tr>
			
			</table>
 	</div>
 	
	<table>
		<tr>
			<td valign="top">
					<s:Grid id="list" url="/roles/grid" multiselect="false" resize="false"  onSelect="onSelectRow">	
						<s:Column width="0" field="id" title="id" hidden="true"/>
						<s:Column width="250" field="name" title="role.name"/>
						<s:Column width="50" field="description" title="common.text.description"  hidden="true"/>
						<s:Column width="0" field="createdBy" title="common.text.createdby" hidden="true"/>
						<s:Column width="0" field="createdDate" title="common.text.createddate" hidden="true"/>
						<s:Column width="0" field="modifiedBy" title="common.text.modifiedby" hidden="true"/>
						<s:Column width="0" field="modifiedDate" title="common.text.modifieddate" hidden="true"/>
					</s:Grid>
	
			</td>
			<td width="40px"></td>
			<td valign="top">
						<s:Grid id="list2" url="/roleUser/roleUserByRoleGrid" multiselect="true" resize="false">	
							<s:Column width="0" field="id" title="id" hidden="true"/>
							<s:Column width="200" field="username" title="userinfo.username"/>
							<s:Column width="200" field="displayName" title="userinfo.displayName" />
							<s:Column width="100" field="department" title="userinfo.department"/>
							<s:Column width="100" field="email" title="userinfo.email"/>
							<s:Column width="0" field="createdBy" title="common.text.createdby" hidden="true"/>
							<s:Column width="0" field="createdDate" title="common.text.createddate" hidden="true"/>
							<s:Column width="0" field="modifiedBy" title="common.text.modifiedby" hidden="true"/>
							<s:Column width="0" field="modifiedDate" title="common.text.modifieddate" hidden="true"/>
						</s:Grid>
		
			</td>
		</tr>
	</table>
