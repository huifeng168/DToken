<%@ page   language="java"    import="java.util.*"   pageEncoding="UTF-8"%>
<%@ page   import="com.connsec.web.*"%>
<%@ taglib prefix="s"  uri="http://www.connsec.com/tags" %>
<%@ taglib prefix="spring"	  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt"		  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c"		  uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
   <!--
      $(function(){	
      	$("#picture").on("click",function(){
      		$("#pictureFile").click();
      	});
      });
      //-->
</script>

<form 
	method="post"
	type="alert"  
	action="<s:Base/>/users/update" 
	forward="<s:Base/>/users/list"
	enctype="multipart/form-data">
<table class="datatable"  id="table_switch_basic" width="980">
	<tbody>				
		<tr>
			<th><spring:message  code="userinfo.username" />：</th>
			<td style="width:251px;">
			<input type="hidden" id="id" name="id" value="${model.id}"/>
				<input readonly type="text" id="username" name="username"  title="" value="${model.username}"/>
				<label for="username"></label>
			</td>
			<th><spring:message  code="userinfo.displayName" />：</th>
			<td>
				<input type="text" id="displayName" name="displayName"  title="" value="${model.displayName}"/>
				<b class="orange">*</b><label for="displayName"></label>
			</td>
			
		</tr>
		<tr>
			<th><spring:message  code="userinfo.familyName" />：</th>
			<td>
				<input type="text" id="familyName" name="familyName"  title="" value="${model.familyName}"/>
				<b class="orange">*</b><label for="familyName"></label>
			</td>
			<th><spring:message  code="userinfo.givenName" />：</th>
			<td>
				<input type="text" id="givenName" name="givenName"  title="" value="${model.givenName}"/>
				<b class="orange">*</b><label for="givenName"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="userinfo.gender" />：</th>
			<td>
				<select name="gender"  class="gender">
						<option value="1"  <c:if test="${1==model.gender}">selected</c:if> ><spring:message  code="userinfo.gender.female" /></option>
						<option value="2"  <c:if test="${2==model.gender}">selected</c:if> ><spring:message  code="userinfo.gender.male" /></option>
				</select>
				<label for="gender"></label>
			</td>
			<th><spring:message  code="userinfo.userType" />：</th>
			<td>
				<input readonly type="text" id="userType" name="userType"  title="" value="${userType.name}"/>

			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;
			</td>
			
		</tr>
				<tr>
			<th><spring:message  code="userinfo.employeeNumber" />：</th>
			<td>
				<input readonly type="text" id="employeeNumber" name="employeeNumber"  title="" value="${model.employeeNumber}"/>
				<b class="orange">*</b><label for="username"></label>
			</td>
			<th><spring:message  code="userinfo.organization" />：</th>
			<td>
				<input type="text" id="organization" name="organization"  title="" value="${model.organization}"/>
				<label for="organization"></label>
			</td>
		</tr>
		
		<tr>
			<th><spring:message  code="userinfo.department" />：</th>
			<td>
				<input type="hidden" id="departmentId" name="departmentId"  title="" value="${model.departmentId}"/>
				<input type="text" id="department" name="department"  title="" value="${model.department}"/>
				<label for="department"></label>
			</td>
			<th><spring:message  code="userinfo.jobTitle" />：</th>
			<td>
				<input type="text" id="jobTitle" name="jobTitle"  title="" value="${model.jobTitle}"/>
				<label for="jobTitle"></label>
			</td>
		</tr>
		<tr><td nowrap align="center" colspan="4">
              	<input id="saveBtn" class="button"      type="submit"   style="width:100px"  value="<spring:message  code="button.text.save" />"/>
                       
     	</td></tr>
	</tbody>
</table>
</form>