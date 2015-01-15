<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
<form id="form" method="post">
	<table class="table table-hover table-condensed" style="padding: 10px 20px 10px 20px;">
		<tr>
            <td width="25%" align="center">品类</td>
			<td width="25%"><input  id="category" name="category" class="span2" value="${maint.category }" disabled="disabled"/></td>
			<td width="25%" align="center">故障名称</td>
			<td width="25%"><input name="faultName" type="text"  class="span2" value="${maint.faultName }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">故障代码</td>
			<td width="25%"><input name="faultCode" type="text" class="span2" value="${maint.faultCode }" disabled="disabled"/></td>
			<td width="25%" align="center">空调P数</td>
			<td width="25%"><input  name="PNumber" class="span2" value="${maint.PNumber }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">维修费用</td>
			<td width="25%"><input name="maintenanceCosts" type="text"  class="span2" value="${maint.maintenanceCosts }" disabled="disabled"/></td>
			<td width="25%" align="center">是否选择</td>
			<td width="25%"><input  name="chose" class="span2" value="${maint.chose }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">是否启用</td>
			<td width="25%"><input  name="wetEnable" class="span2" value="${maint.wetEnable }" disabled="disabled"/></td>
			<td width="25%" align="center">是否关联配件</td>
			<td width="25%"><input  name="wetUnion" class="span2" value="${maint.wetUnion }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">备注</td>
			<td width="25%" ><textarea name="note" disabled="disabled">${maint.note }</textarea></td>
		</tr>
	</table>
</form>
</div>
</div>