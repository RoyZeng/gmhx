<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
		$('#form').form({
			url : '${ctx}/StandardFree/updateHxFree',
			onSubmit : function(param) {
 				param.freeId = $("#freeId").val();
				param.expenseStandard = $("#expenseStandard").val();
				param.managerFee = $("#managerFee").val();
				param.monthlyBonus = $("#monthlyBonus").val();
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(msg) {
				parent.$.messager.progress('close');
				if($.parseJSON(msg).flag == "success"){
					$.messager.alert('提示:','修改成功!');
				}
			}
		});
	});
	
	function submitForm(){
		var f = $("#form");
		f.submit();
	}
	
	function goBack(){
		window.location.href="${ctx}/StandardFree/StandardFreeView";
	}
</script>
<div class="easyui-panel" title="修改安装结算标准资料" style="width: auto">
	<div style="padding: 10px 0 10px 60px">
		<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td>型号<font color="red">*</font>:</td>
					<td><input  class="easyui-combobox"  class="span2" name="model" data-options="url:'${ctx}/hxCode/getCombobox/jx?value=${free.model}'"/></td>
					<td>费用标准<font color="red">*</font>:</td>
					<td><input id="expenseStandard" class="easyui-validatebox span2" type="text" data-options="required:true" value="${free.expenseStandard}" /></td>
				</tr>
				<tr>
					<td>管理费<font color="red">*</font>:</td>
					<td><input id="managerFee" type="text" class="easyui-validatebox span2" data-options="required:true" value="${free.managerFee}" /></td>
				    <td>月度奖励<font color="red">*</font>:</td>
					<td><input id="monthlyBonus" type="text" class="easyui-validatebox span2" data-options="required:true"  value="${free.monthlyBonus}" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div style="text-align: right; padding: 5px">
	<a href="#" class="easyui-linkbutton" onclick="submitForm();">保存</a> <a
		href="#" class="easyui-linkbutton" onclick="goBack();">返回</a>
</div>
<input id="freeId" type="hidden" value="${free.freeId}"/>
