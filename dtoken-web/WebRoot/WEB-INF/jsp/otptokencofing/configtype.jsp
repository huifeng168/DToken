<%@ page 	language="java"   import="java.util.*" 	pageEncoding="UTF-8"%>
<%@ taglib 	prefix="spring"   uri="http://www.springframework.org/tags" %>
<%@ taglib 	prefix="s" uri="http://www.connsec.com/tags" %>
<%@ taglib 	prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="c"       	uri="http://java.sun.com/jsp/jstl/core"  %>

<form method="post" type="label" validate="true" action="<s:Base/>/otptokencofing/updatetype" id="actionForm" >
		<input readonly type="hidden"  name="id"  class="required" title="" value="${model.id}"/>
		<input  type=hidden name="issuer"  class="required" title="" value="${model.issuer}"/>
		<input  type="hidden" name="period"  class="required" title="" value="${model.period}"/>
		<input  type="hidden" name="digits"  class="required" title="" value="${model.digits}"/>
		<input  type="hidden" name="crypto"  class="required" title="" value="${model.crypto}"/>
		<input  type="hidden" name="counter"  class="required" title="" value="${model.counter}"/>
	  <table   class="datatable" >
			<tbody>
			<tr>
				<td >One Time Password Type</td>
			</tr>
			<tr>
				<td  width="50%">
					<table   class="datatable">

						<tr>
							<th><spring:message  code="otp.type" /> :</th>
							<td>
							<select name="type"  class="type">
										<option value="1"  <c:if test="${1==model.type}">selected</c:if> >TimeBased</option>
										<option value="2"  <c:if test="${2==model.type}">selected</c:if> >HOTP</option>
							</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
					    		<input class="button"  type="button"    id="submitBtn" value="<spring:message  code="button.text.save" />"/>
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	  </table>
</form>
