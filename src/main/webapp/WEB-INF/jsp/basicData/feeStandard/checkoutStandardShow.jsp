<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
});

function update(pos_id){
	var feeID = $("#feeID").val();
	window.location.href="${ctx}/standardFee/updateView/"+ feeID;
}

function goBack(){
	window.location.href="${ctx}/standardFee/moveChangeCheckoutView";	
}

</script>
<div class="easyui-panel" title="移换机结算标准详细信息" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<input id="feeID" type="hidden" value="${checkout.feeID}"/>
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td width="25%" align="center">移换机类型</td>
					<td width="25%" ><label>${checkout.operationType}</label></td>
				</tr>	
				<tr>
					<td width="25%" align="center">产品分类</td>
			     	<td width="25%" ><label>${checkout.classifyCode}</label></td>
					<td width="25%" align="center">整机费用</td>
				    <td width="25%" ><label>${checkout.wholefee}</label></td>
				</tr>
				<tr>
				    <td width="25%" align="center">内机费用</td>
				    <td width="25%" ><label>${checkout.innerfee}</label></td>		
					<td width="25%" align="center">外机费用</td>
					<td width="25%" ><label>${checkout.outerfee}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">创建人</td>
					<td width="25%" ><label>${checkout.createUsername}</label></td>
					<td width="25%" align="center">创建时间</td>
					<td width="25%" ><label><fmt:formatDate value="${checkout.createDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
				<tr>
					<td width="25%" align="center">修改人</td>
					<td width="25%" ><label>${checkout.modifyUsername}</label></td>
					<td width="25%" align="center">修改时间</td>
					<td width="25%" ><label><fmt:formatDate value="${checkout.modifyDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>
