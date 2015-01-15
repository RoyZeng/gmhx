<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
});

function update(fitting_id){
	var fittingId = $("#fittingId").val();
	window.location.href="${ctx}/hxFittingLocation/updateView/"+ fittingId;
}

function goBack(){
	window.location.href="${ctx}/hxFittingLocation/hxFittingLocationView";	
}

</script>
<div class="easyui-panel" title="机构详细信息" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<input id="fittingId" type="hidden" value="${hxFittingLocation.fittingId}"/>
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td width="25%" align="center">机构</td>
					<td width="25%" ><label>${hxFittingLocation.organizationName}</label></td>
					<td width="25%" align="center">物料类型</td>
			     	<td width="25%" ><label>${hxFittingLocation.materialType}</label></td>
				</tr>	
				<tr>
					<td width="25%" align="center">配件编码</td>
				    <td width="25%" ><label>${hxFittingLocation.fittingsCode}</label></td>
				    <td width="25%" align="center">配件名称</td>
				    <td width="25%" ><label>${hxFittingLocation.fittingsName}</label></td>
				</tr>
				<tr>
				   <td width="25%" align="center">库位</td>
				   <td width="25%" ><label>${hxFittingLocation.location}</label></td>		
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" ><label>${hxFittingLocation.note}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">创建时间</td>
					<td width="25%" ><label><fmt:formatDate value="${hxFittingLocation.createDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td width="25%" align="center">更新日期</td>
					<td width="25%" ><label><fmt:formatDate value="${hxFittingLocation.modifieDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>
