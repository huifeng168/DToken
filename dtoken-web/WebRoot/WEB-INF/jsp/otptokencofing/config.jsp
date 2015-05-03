<%@ page 	language="java"   import="java.util.*" 	pageEncoding="UTF-8"%>
<%@ taglib 	prefix="spring"   uri="http://www.springframework.org/tags" %>
<%@ taglib 	prefix="s" uri="http://www.connsec.com/tags" %>
<%@ taglib 	prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="c"       	uri="http://java.sun.com/jsp/jstl/core"  %>

<form method="post" type="label" validate="true" action="<s:Base/>/otptokencofing/update" id="actionForm" >
		<input readonly type="hidden"  name="id"  class="required" title="" value="${model.id}"/>
	  <table   class="datatable" >
			<tbody>
			<tr>
				<td >One Time Password Parameters</td>
			</tr>
			<tr>
				<td  width="50%">
					<table   class="datatable">

						<tr>
							<th><spring:message  code="otp.type" />:</th>
							<td>
							<input readonly type="hidden" name="type" class="required" title="" value="${model.type}"/>
							<c:if test="${1==model.type}">
								<input readonly type="text" name="typeTimeBased" class="required" title="" value="TimeBased"/>
							</c:if>
							<c:if test="${2==model.type}">
								<input readonly type="text" name="typeHOTP" class="required" title="" value="HOTP"/>
							</c:if>
							</td>
						</tr>
						<tr>
							<th><spring:message  code="otp.issuer" />:</th>
							<td>
								<input  type="text" name="issuer"  class="required" title="" value="${model.issuer}"/>
							</td>
						</tr>
						<c:if test="${1==model.type}">
						<tr>
							<th><spring:message  code="otp.period" />:</th>
							<td>
								<input  type="text" name="period"  class="required" title="" value="${model.period}"/>
							</td>
						</tr>
						</c:if>
						<tr>
							<th><spring:message  code="otp.digits" />:</th>
							<td>
								<input  type="text" name="digits"  class="required" title="" value="${model.digits}"/>
							</td>
						</tr>
						<tr>
							<th><spring:message  code="otp.crypto" />:</th>
							<td>
								<select name="crypto"  class="crypto">
										<option value="HmacSHA1"  <c:if test="${'HmacSHA1'==model.crypto}">selected</c:if> >HMAC-SHA-1</option>
										<option value="HmacSHA256"  <c:if test="${'HmacSHA256'==model.crypto}">selected</c:if> >HMAC-SHA-256</option>
										<option value="HmacSHA512"  <c:if test="${'HmacSHA512'==model.crypto}">selected</c:if> >HMAC-SHA-512</option>
								</select>
							</td>
						</tr>
						<c:if test="${2==model.type}">
						<tr>
							<th><spring:message  code="otp.counter" />:</th>
							<td>
								<input  type="text" name="counter"  class="required" title="" value="${model.counter}"/>
							</td>
						</tr>
						</c:if>
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
