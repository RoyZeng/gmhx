<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#class1').combobox({
			url: "${ctx}/eccGoods/getGoodClass?classification=1",
			valueField:'value', 
			textField:'text',
			onChange:function(record){  
				$('#class2').combobox({
					url: "${ctx}/eccGoods/getGoodClass?classification=2&upperCode="+$('#class1').combobox('getValue'),
					valueField:'value', 
					textField:'text',
					onChange:function(record){
			        	$('#class3').combobox({
							valueField:'value', 
							textField:'text',
							url: "${ctx}/eccGoods/getGoodClass?classification=3&upperCode="+$('#class2').combobox('getValue'),
							onChange:function(record){
					        	$('#category').combobox({
					        		url: "${ctx}/eccGoods/getGoodClass?classification=4&upperCode="+$('#class3').combobox('getValue'),
									valueField:'value', 
									textField:'text',
								});
					        	$("#category").combobox("clear");
				         	} 
						});
			        	$("#class3").combobox("clear");
			        	$("#category").combobox("clear");
		         	} 
				});
	        	$("#class2").combobox("clear");
	        	$("#class3").combobox("clear");
	        	$("#category").combobox("clear");
	     	} 
		}); 
	});
</script>
<form id="form" method="post">
	<table class="table table-hover table-condensed" style="padding: 10px 20px 10px 20px;">
		<tr>
		    <td width="25%" align="center">一级分类</td>
			<td width="25%"><input id="class1" type="text"  class="easyui-combobox"/></td>
			<td width="25%" align="center">二级分类</td>
			<td width="25%"><input id="class2" type="text"  class="easyui-combobox"></td>
		</tr>
		<tr>
			<td width="25%" align="center">三级分类</td>
			<td width="25%"><input id="class3" type="text"  class="easyui-combobox"></td>
			<td width="25%" align="center">品类</td>
			<td width="25%"><input class="easyui-combobox" id="category" name="category" class="span2"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">故障名称</td>
			<td width="25%"><input name="faultName" type="text"  class="easyui-validatebox span2" data-options="required:true"></td>
			<td width="25%" align="center">故障代码</td>
			<td width="25%"><input name="faultCode" type="text" class="easyui-validatebox span2" data-options="required:true"></td>
		</tr>
		<tr>
			<td width="25%" align="center">空调P数</td>
			<td width="25%"><input class="easyui-combobox" name="PNumber" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/ktps'"/></td>
			<td width="25%" align="center">维修费用</td>
			<td width="25%"><input name="maintenanceCosts" type="text"  class="easyui-validatebox span2" data-options="required:true"></td>
		</tr>
		<tr>
			<td width="25%" align="center">是否选择</td>
			<td width="25%"><input class="easyui-combobox" name="chose" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/sf'"/></td>
			<td width="25%" align="center">是否启用</td>
			<td width="25%"><input class="easyui-combobox" name="wetEnable" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/sf'"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">是否关联配件</td>
			<td width="25%"><input class="easyui-combobox" name="wetUnion" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/sf'"/></td>
			<td width="25%" align="center">备注</td>
			<td width="25%" ><textarea name="note" placeholder="请输入备注"></textarea></td>
		</tr>
	</table>
</form>