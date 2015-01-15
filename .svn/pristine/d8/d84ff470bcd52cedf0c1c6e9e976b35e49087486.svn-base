<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		var msg='${list}';
		if(msg){
			var jsons = $.parseJSON(msg);
			$("#checkedUser").html("");
			$.each(jsons.rows,function(i, item) {
				$("#checkedUser").append(
				"<tr id=\""+item.customer_id+"\"><td><a href=\"#\" onclick=\"selectCustomer(\'"+item.customer_id+"\');\">"+item.customer_name+"</a></td>"+
				"<td>"+(item.phone == null ? "":item.phone)+"</td><td>"+(item.address == null ? "":item.address)+"</td><td>"+(item.internal_machine_code1 == null ? "":item.internal_machine_code1)+"</td><td>"+
				(item.external_machine_code == null ? "":item.external_machine_code)+"</td></tr>");
			});
		}
	});
	
	function selectCustomer(id) {
		$("tbody tr").css("background",'#FFFFFF');
		$("#"+id).css("background",'#C0C0C0');
		$("#checkedId").val(id);
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<input type="hidden" id="checkedId">
		<table border=1 style="width:600px;height:auto" class="table table-hover table-condensed"> 
		    <thead>
		        <tr align='center'>   
		            <th>姓名</th>   
		            <th>联系电话</th>   
		            <th>联系地址</th> 
		            <th>内机码</th> 
		            <th>外机码</th> 
		        </tr> 
		    </thead>
		    <tbody id="checkedUser" align='center'></tbody>
		</table>
	</div>
</div>

