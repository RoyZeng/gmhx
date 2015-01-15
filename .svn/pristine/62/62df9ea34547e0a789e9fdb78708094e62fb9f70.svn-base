<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
});

function update(pos_id){
	var posId = $("#posId").val();
	window.location.href="${ctx}/hxPostage/updateView/"+ posId;
}

function goBack(){
	window.location.href="${ctx}/hxPostage/hxPostageView";	
}

</script>
<div class="easyui-panel" title="机构详细信息" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<input id="posId" type="hidden" value="${hxPostage.posId}"/>
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td width="25%" align="center">发件单位</td>
					<td width="25%" ><label>${hxPostage.posSendUnit}</label></td>
					<td width="25%" align="center">收件单位</td>
			     	<td width="25%" ><label>${hxPostage.posReceiptUnit}</label></td>
				</tr>	
				<tr>
					<td width="25%" align="center">收件人</td>
				    <td width="25%" ><label>${hxPostage.posRecipient}</label></td>
				</tr>
				<tr>
				   <td width="25%" align="center">收件人地址</td>
				   <td width="25%" ><label>${hxPostage.posAddress}</label></td>		
				</tr>
				<tr>
					<td width="25%" align="center">邮寄付款单位</td>
					<td width="25%" ><label>${hxPostage.posPayUnit}</label></td>
				    <td width="25%" align="center">邮寄单号</td>
				    <td width="25%" ><label>${hxPostage.posNum}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮寄方式</td>
					 <td width="25%" ><label>${hxPostage.posWay}</label></td>
					<td width="25%" align="center">邮寄日期</td>
					<td width="25%" ><label><fmt:formatDate value="${hxPostage.posDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮寄内容</td>
					<td width="25%" ><label>${hxPostage.posContent}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">邮寄单位</td>
					<td width="25%" ><label>${hxPostage.posUnit}</label></td>
					<td width="25%" align="center">重量</td>
					<td width="25%" ><label>${hxPostage.posWeight}</label></td>
				</tr>
				<tr>
				    <td width="25%" align="center">金额</td>
				    <td width="25%" ><label>${hxPostage.posMoney}</label></td>
					<td width="25%" align="center">经手人</td>
					<td width="25%" ><label>${hxPostage.posHandlers}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" ><label>${hxPostage.posNote}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">创建时间</td>
					<td width="25%" ><label><fmt:formatDate value="${hxPostage.posCreateDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td width="25%" align="center">更新日期</td>
					<td width="25%" ><label><fmt:formatDate value="${hxPostage.posUpdateDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>
