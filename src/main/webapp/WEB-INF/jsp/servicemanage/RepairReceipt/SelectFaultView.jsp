<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript">
	
 	$(function() {
 		
 		
		parent.$.messager.progress('close');
       	$('#faultCode').combobox({
			valueField:'value', 
			textField:'text',
			url: '${ctx}/hxCode/getFittingInfo?faultCode=${faultCode}&suitModel=${machineType}&brand=${brand}&category=${category}',
			onSelect: function(rec){ 
				$("#partCode").val(rec.value);	        	
				$("#partName").val(rec.text);	        	
				$("#partPrice").val(rec.price);	
			}
		});
	}); 

</script>
<input type="hidden" id="partCode">
<input type="hidden" id="partName">
<input type="hidden" id="partPrice">
<form id="searchForm">
	<table class="table table-hover table-condensed" style="width:100%; padding: 30px 15px 15px 15px">
	 <tr align="center">
	 	<td width="30%" align="center">配件编码:<input size="35" name="faultCode"  id="faultCode" type="text" data-options="panelHeight:'100'"  class="easyui-combobox" ></td>
	 </tr> 
 </table>
</form>