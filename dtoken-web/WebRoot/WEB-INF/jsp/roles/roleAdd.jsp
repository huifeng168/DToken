<%@ page   language="java"  import="java.util.*"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt"     uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib 	prefix="s" uri="http://www.connsec.com/tags" %>

<form id="actionForm"  method="post" type="label" autoclose="true"  action="<s:Base/>/roles/add"> 
	        <!-- content -->    
	      	<!--table-->
	  <table border="0" cellpadding="0" cellspacing="0" class="th_atleft">
		<tbody>
		<tr>
			<th><spring:message  code="role.name" />：</th>
			<td nowrap>
				<span class="intspan"><input type="text" id="name" name="name" class="int required" title="" value="${role.name}"/></span>
				<b class="orange">*</b><label for="name"></label>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input id="_method" type="hidden" name="_method"  value="post"/>
				<input id="status" type="hidden" name="status"  value="1"/>
	    		<input  type="button"    id="submitBtn" value="<spring:message  code="button.text.save" />"/>
  				<input  type="button"    id="backBtn" value="<spring:message  code="button.text.cancel" /> "/>	
				
			</td>
		</tr>
		</tbody>
	  </table>
</form>