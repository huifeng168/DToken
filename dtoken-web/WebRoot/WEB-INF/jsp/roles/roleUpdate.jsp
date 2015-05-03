<%@ page   language="java"      import="java.util.*"     pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"		uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="http://www.connsec.com/tags" %>

<div class="mainwrap">
	<form id="actionForm"  method="post" type="label"  action="<s:Base/>/roles/update">
		 <div class="golBlock tableBlock" style="width:400px;">
			<div class="top">
				<div class="topin"><h3><spring:message  code="system.sysrole.modify.dialog.title" /></h3></div>
			</div>
	
		    <div class="main">
		    <div class="mainin">			 
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
					</tbody>
				  </table>
	  	        <div class="clear"></div>
			</div>
			</div>
				<div class="btm btm_btn">
					<div class="btmin btmin_btn">
						<input id="_method" type="hidden" name="_method"  value="put"/>
						<input id="status" type="hidden" name="status"  value="1"/>
						<input id="id" type="hidden" name="id"  value="${role.id}"/>
			    		<input  id="submitBtn" type="button" value="<spring:message  code="button.text.save" />">
		  				<input  id="backBtn"   type="button" value="<spring:message  code="button.text.cancel" />">			  
					</div>
				</div>
		 </div> 
	</form>
</div>

