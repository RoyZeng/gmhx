<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">

function update(){
	var freeId = $("#freeId").val();
	window.location.href="${ctx}/StandardFree/updateView/" + freeId;  
}

function goBack(){
	window.location.href="${ctx}/StandardFree/StandardFreeView";
}
</script>
<div class="easyui-panel" title="安装结算标准详细信息" style="width: auto">
	<div style="padding: 10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td>型号</td>
					<td><label>${free.model}</label></td>
					<td>费用标准</td>
					<td><label>${free.expenseStandard}</label></td>
				</tr>
				<tr>
					<td>管理费</td>
					<td><label>${free.managerFee}</label></td>
				    <td>月度奖励</td>
				    <td><label>${free.monthlyBonus}</label></td>
				</tr>
				<tr>
					<td>创建人</td>
					<td><label>${free.founder}</label></td>
					<td>创建时间</td>
					<td><label><fmt:formatDate value="${free.createDate}"
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
<input id="freeId" type="hidden" value="${free.freeId}"/>