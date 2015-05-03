<%@ page   contentType="text/html; charset=UTF-8" import="java.util.Map,java.util.LinkedHashMap" %>
<%@ page   import="com.connsec.web.*"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"	uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" 	uri="http://www.connsec.com/tags" %>


<script type="text/javascript">

function onClick (event, treeId, treeNode) {
	  $("#actionForm").clearForm();
	  $("#actionForm").json2form({data:treeNode.data});
	  $("#_method").val("put");
	  $("#status").val("1");
	 
}
	
$(function () {
	$("#addChildBtn").click(function(){
		var nodes = $.fn.zTree.getZTreeObj("orgsTree").getSelectedNodes();
		if (nodes.length == 0) {
			$.alert({content:"<spring:message  code="system.menus.alert.select.pmenu" />"});
			return;
		}
		$("#actionForm").clearForm();
		$("#pId").val(nodes[0].data.id);
	  	$("#pName").val(nodes[0].data.name);
	  	$("#sortOrder").val(1);
	  	$("#status").val("1");
	    $("#_method").val("post");
	});	
	
	
	$("#saveBtn").click(function(){
		if($("#_method").val()=="put"){
			$("#actionForm").attr("action",'<c:url value="/orgs/update"/>');
		}else{
			$("#actionForm").attr("action",'<c:url value="/orgs/add"/>');
		}
		
		var xpath =	$.fn.zTree.getZTreeObj("orgsTree").getNodeByParam("id",$("#pId").val()).data.xPath;
		if(xpath=="<%=WebContext.getSystemNavRootId()%>"||xpath==undefined||xpath==""){
				xpath ="/"+$("#id").val();
			}else{
				xpath = xpath+"/"+$("#id").val();
			}
			
		
			//alert(xpath);
			$("#xPath").val(xpath);
			if($("#_method").val()=="post"){
				var node=$("#actionForm").serializeObject();
				node.data=$("#actionForm").serializeObject();
				delete node['url'];
				$.fn.zTree.getZTreeObj("orgsTree").addNodes(
					$.fn.zTree.getZTreeObj("orgsTree").getSelectedNodes()[0],node);
			}else{
				var node=$("#actionForm").serializeObject();
				node.data=$("#actionForm").serializeObject();
				node=$.extend( $.fn.zTree.getZTreeObj("orgsTree").getSelectedNodes()[0],node);
				delete node['url'];
				$.fn.zTree.getZTreeObj("orgsTree").updateNode(node);
			}
			$('#actionForm').submit(); 
		});	
		
		
		$("#deleteBtn").click(function(){
			$.post('<c:url value="/orgs/delete"/>',{ id:$("#id").val(),_method:"delete"}, function(data) {
				$.fn.zTree.getZTreeObj("orgsTree").removeNode($.fn.zTree.getZTreeObj("orgsTree").getSelectedNodes()[0]);
  				$.alert({content:data.message});
			});
		});
});
</script>
	 
	     <!-- content -->  
 <table class="datatable"   width="100%" >
   <tr>
      <td valign="top"  class="td_1" style="vertical-align: top;">
          <s:Tree rootId="<%=WebContext.getSystemNavRootId()%>" url="/orgs/tree" id="orgsTree" onClick="onClick"/>
      </td>
      <td  valign="top"  class="td_1" style="vertical-align: top;">
         <div id="orgsTable" style="PADDING:0;MARGIN: 0;width:650px"></div>
         <form  id="actionForm" action='<c:url value="/orgs/add"/>' method="post">
         	<table>
         		<tr>
         			<td>
						<ul class="switch_tab"  style="width:100%" >
							<li id="switch_common" value="table_switch_common" style="width:100%" class="switch_tab_class switch_tab_current"><a href="javascript:void(0);"><spring:message  code="org.tab.basic" /></a></li>
						</ul>
					</td>
         		</tr>
         		<tr><td>
         	<table id="table_switch_common"  class="datatable"  width="600px">
               <tr style="display:none">
                  <th ><input type="text" id="status" type="hidden" name="status"  value="1"/>
                  <input type="text" id="_method" type="hidden" name="_method"  value="put"/></th>
                  <td></td>
               </tr>
               <tr >
                  <th ><spring:message  code="org.pid" />：</th>
                  <td><span class="intspan"><input type="text" id="pId" name="pId" size="80" class="int"/></span></td>
               </tr>
               <tr>
                  <th  width="200px"><spring:message  code="org.pname" />：</th>
                  <td><span class="intspan"><input type="text" id="pName" name="pName" size="80"   class="int"/></span></td>
               </tr>
               <tr >
                  <th ><spring:message  code="org.id" />：</th>
                  <td><span class="intspan"><input type="text" id="id" name="id" size="80"   class="int"/></span></td>
               </tr>
               <tr>
                  <th ><spring:message  code="org.name" />：</th>
                  <td><span class="intspan"><input type="text"  id="name" name="name"  size="80"  class="int"/></span></td>
               </tr>
               <tr>
                  <th ><spring:message  code="org.fullname" />：</th>
                  <td><span class="intspan"><input type="text"  id="fullName" name="fullName"  size="80"  class="int"/></span></td>
               </tr>
               <tr>
                  <th >
                     <spring:message  code="common.text.sortorder" /> ：
                  </th>
                  <td><span class="intspan"><input type="text"  id="sortOrder" name="sortOrder"  size="80"  class="int"/></span></td>
               </tr>
               <tr  style="display:none">
                  <th ><spring:message  code="org.xpath" /> ：
                  </th>
                  <td><span class="intspan"><input type="text"  id="xPath" name="xPath" size="80"   class="int"/></span></td>
               </tr>
               
               <tr>
                  <th ><spring:message  code="common.text.description" />：</th>
                  <td><span class="intspan"><input type="text"  id="description" name="description"  size="80"  class="int"/></span></td>
               </tr>
            </table>
           
         		</td></tr>
         		<tr><td nowrap align="center">
                              <input id="addChildBtn" class="button"   type="button" style="width:120px"  value="<spring:message  code="button.text.add" />"/>
                          
                              <input id="saveBtn" class="button"      type="button"   style="width:100px"  value="<spring:message  code="button.text.save" />"/>
                           
                              <input id="deleteBtn"  class="button"   type="button"   style="width:100px"  value="<spring:message  code="button.text.delete" />"/>
                           
         		</td></tr>
         	</table>

         </form>
      </td>
   </tr>
</table>
