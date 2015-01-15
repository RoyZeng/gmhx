<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">

function update(freeCode){
	var freeCode = $("#freeCode").val();
	window.location.href="${ctx}/StandardSetupFree/updateView/" + freeCode;  
}

function goBack(){
	window.location.href="${ctx}/StandardSetupFree/StandardSetupFreeView";
}
</script>
<div class="easyui-panel" title="安装结算标准详细信息" style="width: auto">
	<div style="padding: 10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td>型号</td>
					<td><label>${free.model}</label></td>
					<td>机构</td>
					<td><label>${free.freeOrganization}</label></td>
				</tr>
				<tr>
					<td>费用</td>
					<td><label>${free.freeCost}</label></td>
				    <td>管理费</td>
				    <td><label>${free.managerCost}</label></td>
				</tr>
				<tr>
					<td>创建人</td>
					<td><label>${free.founders}</label></td>
					<td>创建时间</td>
					<td><label><fmt:formatDate value="${free.founderDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
				<tr>
					<td>修改人</td>
					<td><label>${free.modified}</label></td>
					<td>修改时间</td>
					<td><label><fmt:formatDate value="${free.modifiedDate}"
								pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align: right; padding: 5px">
		<a href="#" class="easyui-linkbutton" onclick="update();">修改</a> <a
			href="#" class="easyui-linkbutton" onclick="goBack();">返回</a>
	</div>
</div>
<input id="freeCode" type="hidden" value="${free.freeCode}"/>