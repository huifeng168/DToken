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

<form  method="post" type="alert"  action="<s:Base/>/users/add" forward="<s:Base/>/users/list">
 <table id="table_switch_basic" width="980" class="datatable" >
	<tbody>				
	<tr>
		<th><spring:message  code="userinfo.username" />：</th>
		<td style="width:251px;">
		<input type="hidden" id="id" name="id" value=""/>
			<input type="text" id="username" name="username"  title="" value=""/>
			<b class="orange">*</b><label for="username"></label>
		</td>
		<th><spring:message  code="userinfo.password" />：</th>
		<td>
			<input type="password" id="password" name="password"  title="" value=""/>
			<b class="orange">*</b><label for="password"></label>
		</td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;
		</td>
		
	</tr>
	<tr>
		<th><spring:message  code="userinfo.displayName" />：</th>
		<td>
			<input type="text" id="displayName" name="displayName"  title="" value=""/>
			<b class="orange">*</b><label for="displayName"></label>
		</td>
	</tr>
	<tr>
		<th><spring:message  code="userinfo.familyName" />：</th>
		<td>
			<input type="text" id="familyName" name="familyName"  title="" value=""/>
			<b class="orange">*</b><label for="familyName"></label>
			
		</td>
		<th><spring:message  code="userinfo.givenName" />：</th>
		<td>
			<input type="text" id="givenName" name="givenName"  title="" value=""/>
			<b class="orange">*</b><label for="givenName"></label>
		</td>
	</tr>
	<tr>
		<th><spring:message  code="userinfo.gender" />：</th>
		<td>
			<select name="gender"  class="gender">
					<option value="1"  selected><spring:message  code="userinfo.gender.female" /></option>
					<option value="2"  ><spring:message  code="userinfo.gender.male" /></option>
			</select><label for="gender"></label>
		</td>
		<th><spring:message  code="userinfo.userType" />：</th>
		<td>
			<span class="sellist">
				<select name="userType"  class="select_t">
					<c:forEach items="${userTypeList}" var="userType">
						<option value="${userType.id}"  <c:if test="${'EMPLOYEE'==userType.id}">selected</c:if>>${userType.name}</option>
		      		</c:forEach>
				</select>
			</span>
		</td>
	</tr>

	<tr>
		<td colspan="4">&nbsp;
		</td>
		
	</tr>
		<tr>
			<th><spring:message  code="userinfo.employeeNumber" />：</th>
			<td>
				<input type="text" id="employeeNumber" name="employeeNumber"  title="" value=""/>
				<label for="employeeNumber"></label>
			</td>
			<th><spring:message  code="userinfo.organization" />：</th>
			<td>
				<input type="text" id="organization" name="organization"  title="" value=""/>
				<label for="organization"></label>
			</td>
			
			
		</tr>
		
		<tr>
			<th><spring:message  code="userinfo.department" />：</th>
			<td>
				<input type="hidden" id="departmentId" name="departmentId"  title="" value=""/>
				<input type="text" id="department" name="department"  title="" value=""/>
				<label for="department"></label>
			</td>
			<th><spring:message  code="userinfo.jobTitle" />：</th>
			<td>
				<input type="text" id="jobTitle" name="jobTitle"  title="" value=""/>
				<label for="jobTitle"></label>
			</td>
		</tr>
		<tr><td nowrap align="center" colspan="4">
              	<input id="saveBtn" class="button"      type="submit"   style="width:100px"  value="<spring:message  code="button.text.save" />"/>
                       
     	</td></tr>
	</tbody>
 </table>
</form>
