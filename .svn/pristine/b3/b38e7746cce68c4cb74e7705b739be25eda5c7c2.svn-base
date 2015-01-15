<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript">
	
	$(function() {
		parent.$.messager.progress('close');
        	$('#classifyCode').combobox({
				valueField:'value', 
				textField:'text',
				url: "${ctx}/eccGoods/getGoodClass?classification=4&upperCode=C20",
				onChange:function(record){
		        	$('#faultCode').combobox({
		        		url: "${ctx}/eccGoods/getFaultByCategory?category="+record,
						valueField:'value', 
						textField:'text',
						onSelect : function(record){
							$("#faultCode").val(record.value);
							$("#faultName").val(record.text);
						}
					});
	         	} 
		});
	});

</script>
	<input type="hidden" id="faultName">
	<form id="searchForm">
		<table class="table table-hover table-condensed" style="width:100%; padding: 30px 15px 15px 15px">
		 <tr align="center">
		 	<td width="30%" align="right">服务措施:</td>
			<td width="70%" align="left"><input name="classifyCode"  id="classifyCode" type="text"  class="easyui-combobox" ></td>
		 </tr> 
		<tr align="center">
			<td width="30%" align="right">故障:</td>
	    	<td width="70%"  align="left"><input name="faultCode" id="faultCode" type="text" class="easyui-combobox"></td>
            </tr>
	 </table>
	</form>