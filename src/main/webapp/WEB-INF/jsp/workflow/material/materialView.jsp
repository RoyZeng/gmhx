<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<script type="text/javascript">
$(function() {
	$('#dataGrid').datagrid({
		title : "配件明细",
		url : "${ctx}/hxMaterial/getHxMaterialDetails/${m.listNumber}",
		//height : document.documentElement.clientHeight * 0.3,
		striped : true,
		collapsible : true,
		autoRowHeight : false,
		singleSelect : true,
		remoteSort : false,
		rownumbers : true,
		fitColumns : true,
		showFooter: true,
		columns : [ [ {
			field : 'fittingCode',
			title : '配件编码',
			align:'center',
			width : 300
		}, {
			field : 'fittingName',
			title : '配件名称',
			align : 'center',
			width : 300
		}, 
		{
			field : 'spec',
			title : '规格',
			align : 'center',
			styler: cellStyler,
			width : 300
		}, {
			field : 'price',
			title : '单价',
			align : 'center',
			styler: cellStyler,
			width : 300
		}, {
			field : 'applyCount',
			title : '申请数',
			align:'center',
			width : 300
		},{
			field : 'auditCount',
			title : '审批数',
			align:'center',
			width : 300
		},{
			field : 'comment',
			title : '备注',
			align:'center',
			width : 300
		} ] ]
	});
	
	
	$("div.datagrid-wrap").css("height","auto");
	
	function cellStyler(value,row,index){
		if(row.spec=="总计（元）："){
			return 'background-color:#ffee00;color:red;';
		}
	}
});
</script>

<div>
<form id="form" method="post">
	<div style="background-color:#95b8e7; text-align:left; color:#fff; font-weight:bold; border-bottom: 1px solid #000;">
		详细查看${title}
	</div>
	<table border=1 style="cellSpacing:1;cellPadding:3;width:100%;background-color:#eff5ff;border-collapse:collapse">
		<tr>
			<td width="25%" align="center">单据编号</td>
			<td width="25%">${m.listNumber}</td>
			<td width="25%" align="center">单据类型</td>
			<td width="25%">${title}</td>
		</tr>
		<c:if test="${m.type=='fb-kc-zy'}">
			<tr>
				<td width="25%" align="center">良品,残品转移方向</td>
				<c:choose>
				   <c:when test="${m.moveDirection =='0'}"> 
				   			<td width="25%">良品转残品</td>
				   </c:when>
				    <c:when test="${m.moveDirection =='1'}"> 
				   			<td width="25%">残品转良品</td>
				   </c:when>
				 </c:choose>
			</tr>
		</c:if>
		<tr>
			<td width="25%" align="center">发货单位</td>
			<td width="25%"><input id="sendCompany" class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.sendCompany}'" disabled="disabled"/></td>
			<td width="25%" align="center">收货单位</td>
			<td width="25%"><input id="receiveCompany" class="easyui-combobox" name="receiveCompany" data-options="url:'${ctx}/hxCode/getOrgCombobox?value=${m.receiveCompany}'" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="25%" align="center">状态</td>
			<td width="25%"><input class="easyui-combobox" name="sendCompany" data-options="url:'${ctx}/hxCode/getStatusCombobox?status=${m.status}'" disabled="disabled"/></td>
			<td width="25%" align="center"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td width="25%" align="center">申请人</td>
			<td width="25%">${m.applicant}</td>
			<td width="25%" align="center">申请日期</td>
			<td width="25%"><fmt:formatDate value="${m.applyDate}" pattern="yyyy-MM-dd"/></td>
		</tr>
			<c:if test="${type=='wd-yjjh-sq'}">
		<tr>
			<td width="25%" align="center">客户姓名</td>
			<td width="25%">${m.customerName}</td>
			<td width="25%" align="center">客户电话</td>
			<td width="25%">${m.customerTel}</td>
		</tr>
		<tr>
			<td width="25%" align="center">地址</td>
			<td width="25%" colspan="3">${m.address}</td>
		</tr>
			</c:if>
		<tr>
			<td width="25%" align="center">备注</td>
			<td width="25%" colspan="3">${m.comment}</td>
		</tr>
		<tr>
			<td width="25%" align="center">创建人</td>
			<td width="25%">${m.createPerson}</td>
			<td width="25%" align="center">创建时间</td>
			<td width="25%"><fmt:formatDate value="${m.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td width="25%" align="center">修改人</td>
			<td width="25%">${m.updatePerson}</td>
			<td width="25%" align="center">修改时间</td>
			<td width="25%"><fmt:formatDate value="${m.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</table>
</form>
</div>
<div class="easyui-layout">
	<table id="dataGrid"></table>
</div>

