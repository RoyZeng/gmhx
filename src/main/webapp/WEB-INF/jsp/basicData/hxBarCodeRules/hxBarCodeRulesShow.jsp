<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	
$(function() {
	parent.$.messager.progress('close');
});

function update(rules_id){
	var rulesId = $("#rulesId").val();
	window.location.href="${ctx}/hxBarCodeRules/updateView/"+ rulesId;
}

function goBack(){
	window.location.href="${ctx}/hxBarCodeRules/hxBarCodeRulesView";	
}

</script>
<div class="easyui-panel" title="机构详细信息" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<input id="rulesId" type="hidden" value="${hxBarCodeRules.rulesId}"/>
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td width="25%" align="center">国美代码</td>
					<td width="25%" ><label>${hxBarCodeRules.gomeCode}</label></td>
					<td width="25%" align="center">品类</td>
			     	<td width="25%" ><label>${hxBarCodeRules.category}</label></td>
				</tr>	
				<tr>
					<td width="25%" align="center">条码位数</td>
				    <td width="25%" ><label>${hxBarCodeRules.barCodeNumber}</label></td>
				</tr>
				<tr>
				   <td width="25%" align="center">内机位</td>
				   <td width="25%" ><label>${hxBarCodeRules.insideMachine}</label></td>
				   <td width="25%" align="center">内机位内容</td>
				   <td width="25%" ><label>${hxBarCodeRules.insideMachineContent}</label></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">内机位1</td>
				   <td width="25%" ><label>${hxBarCodeRules.insideMachineOne}</label></td>
				   <td width="25%" align="center">内机位内容1</td>
				   <td width="25%" ><label>${hxBarCodeRules.insideMachineContentOne}</label></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">内机位2</td>
				   <td width="25%" ><label>${hxBarCodeRules.insideMachineTwo}</label></td>
				   <td width="25%" align="center">内机位内容2</td>
				   <td width="25%" ><label>${hxBarCodeRules.insideMachineContentTwo}</label></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">外机位</td>
				   <td width="25%" ><label>${hxBarCodeRules.outsideMachine}</label></td>
				   <td width="25%" align="center">外机位内容</td>
				   <td width="25%" ><label>${hxBarCodeRules.outsideMachineContent}</label></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">外机位2</td>
				   <td width="25%" ><label>${hxBarCodeRules.outsideMachineOne}</label></td>
				   <td width="25%" align="center">外机位内容1</td>
				   <td width="25%" ><label>${hxBarCodeRules.outsideMachineContentOne}</label></td>			
				</tr>
				<tr>
				   <td width="25%" align="center">外机位2</td>
				   <td width="25%" ><label>${hxBarCodeRules.outsideMachineTwo}</label></td>
				   <td width="25%" align="center">外机位内容2</td>
				   <td width="25%" ><label>${hxBarCodeRules.outsideMachineContentTwo}</label></td>			
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" ><label>${hxBarCodeRules.note}</label></td>
				</tr>
				<tr>
					<td width="25%" align="center">创建时间</td>
					<td width="25%" ><label><fmt:formatDate value="${hxBarCodeRules.createDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
					<td width="25%" align="center">更新日期</td>
					<td width="25%" ><label><fmt:formatDate value="${hxBarCodeRules.modifieDate}" pattern='yyyy-MM-dd HH:mm:ss' /></label></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="update();">修改</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>
