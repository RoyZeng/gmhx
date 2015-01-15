<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
});

function update(){
	var menuId = $("#menuId").val();
	window.location.href="${ctx}/hxMenu/updateView?menuId="+menuId;
}

function goBack(){
	window.location.href="${ctx}/hxMenu/menuView";
}

</script>
	<form id="form" method="post">
			<div style="background-color:#95b8e7; text-align:center; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
				功能菜单详细信息
			</div>
			<input id="menuId" type="hidden" value="${menu.menuId}"/>
			<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse;">
				<tr>
					<td width="25%" align="center">菜单名称:</td>
					<td width="25%"><label>${menu.menuName}</label></td>
					<td width="25%" align="center">菜单路径:</td>
					<td width="25%"><label>${menu.url}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">菜单图标位置:</td>
					<td width="25%"><label>${menu.iconUrl}</label></td>
					<td width="25%" align="center">序号:</td>
					<td width="25%"><label>${menu.sort}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">父菜单:</td>
					<td width="25%"><label>${menu.parentName}</label></td>
					<td width="25%" align="center">是否叶子节点:</td>
					<td width="25%">
						<c:if test="${menu.isLeafNode=='0'}">
							<label>否</label>
						</c:if>
						<c:if test="${menu.isLeafNode=='1'}">
							<label>是</label>
						</c:if>
					</td>
				</tr>
				<tr>
					<td width="25%" align="center">角色描述:</td>
					<td width="25%" colspan="3"><label>${menu.menuDesc}</label></td>
				</tr>
			</table>
	</form>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-edit'" onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton"  data-options="toggle:true,group:'g1',iconCls:'icon-back'" onclick="goBack();">返回</a>
	</div>
</div>