<%@ page 	language="java"   import="java.util.*" 	pageEncoding="UTF-8"%>
<%@ taglib 	prefix="spring"   uri="http://www.springframework.org/tags" %>
<%@ taglib 	prefix="s" uri="http://www.connsec.com/tags" %>
<%@ taglib 	prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib 	prefix="s" uri="http://www.connsec.com/tags" %>

<form id="actionForm"  method="post" type="label" autoclose="true"  action="<s:Base/>/company/add">
 
	        <!-- content -->    
	      	<!--table-->
	<table width="960"  cellspacing="0" cellpadding="0" class="ui-widget-content uuamcontettable" border="2">
		<tbody>
		<tr>
			<th><spring:message  code="company.shortname" />：</th>
			<td>
				<input type="text" id="shortName" name="shortName"  title="" value=""/>
				<b class="orange">*</b><label for="shortName"></label>
			</td>
			<th><spring:message  code="company.fullname" />：</th>
			<td>
				<input type="text" id="fullName" name="fullName"  title="" value=""/>
				<b class="orange">*</b><label for="fullName"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.division" />：</th>
			<td>
				<input type="text" id="division" name="division"  title="" value=""/>
				<b class="orange">*</b><label for="division"></label>
			</td>
			<th><spring:message  code="company.category" />：</th>
			<td>
				<input type="text" id="category" name="category"  title="" value=""/>
				<b class="orange">*</b><label for="category"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.representative" />：</th>
			<td>
				<input type="text" id="representative" name="representative"  title="" value=""/>
				<b class="orange">*</b><label for="representative"></label>
			</td>
			<th><spring:message  code="company.website" />：</th>
			<td>
				<input type="text" id="webSite" name="webSite"  title="" value=""/>
				<b class="orange">*</b><label for="webSite"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.phone" />：</th>
			<td>
				<input type="text" id="phone" name="phone"  title="" value=""/>
				<b class="orange">*</b><label for="phone"></label>
			</td>
			<th><spring:message  code="company.fax" />：</th>
			<td>
				<input type="text" id="fax" name="fax"  title="" value=""/>
				<b class="orange">*</b><label for="fax"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.email" />：</th>
			<td>
				<input type="text" id="email" name="email"  title="" value=""/>
				<b class="orange">*</b><label for="email"></label>
			</td>
			<th><spring:message  code="company.postalcode" />：</th>
			<td>
				<input type="text" id="postalCode" name="postalCode"  title="" value=""/>
				<b class="orange">*</b><label for="postalCode"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.country" />：</th>
			<td>
				<input type="text" id="country" name="country"  title="" value=""/>
				<b class="orange">*</b><label for="country"></label>
			</td>
			<th><spring:message  code="company.region" />：</th>
			<td>
				<input type="text" id="region" name="region"  title="" value=""/>
				<b class="orange">*</b><label for="region"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.locality" />：</th>
			<td>
				<input type="text" id="locality" name="locality"  title="" value=""/>
				<b class="orange">*</b><label for="locality"></label>
			</td>
			<th><spring:message  code="company.street" />：</th>
			<td>
				<input type="text" id="street" name="street"  title="" value=""/>
				<b class="orange">*</b><label for="street"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.license" />：</th>
			<td>
				<input type="text" id="license" name="license"  title="" value=""/>
				<b class="orange">*</b><label for="license"></label>
			</td>
			<th><spring:message  code="company.creationdate" />：</th>
			<td>
				<input type="text" id="creationDate" name="creationDate"  title="" value=""/>
				<b class="orange">*</b><label for="creationDate"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message  code="company.description" />：</th>
			<td>
				<input  type="text" id="description" name="description"  title="" value=""/>
				<b class="orange">*</b><label for="description"></label>
			</td>
			<th></th>
			<td>
				
			</td>
		</tr>
		</tbody>
	  </table>
			<input id="_method" type="hidden" name="_method"  value="post"/>
			<input id="status" type="hidden" name="status"  value="1"/>
    		<input  id="submitBtn" type="button" value="<spring:message  code="button.text.save" />">
 			<input  id="backBtn"   type="button" value="<spring:message  code="button.text.cancel" />">
				
</form>