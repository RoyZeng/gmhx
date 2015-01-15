<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript"> 
 $(function() {
	
	parent.$.messager.progress('close');
});

function submitForm(){
	$.post("${ctx}/StandardFree/addHxFree",$('#form').serialize(),function(returnObj){
		if(returnObj.flag="success"){
			$.messager.alert('提示:','保存成功!');
		}else{
			$.messager.alert('提示:','保存失败!');
		}
	});
}
	
 	function goBack(){
 		window.location.href="${ctx}/StandardFree/StandardFreeView";
 	}
 	function continueToAdd(){
		//继续添加标志位置1
 		$("#continueToAddFlag").val("1");
		var f = $("#form");
 		f.submit();
 	}
 </script>
<div class="easyui-panel" title="新建安装结算标准资料" style="width: auto">
	<div style="padding: 10px 0 10px 60px">
		<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
					<td>型号<font color="red">*</font>:</td>
					<td><input  class="easyui-combobox"  class="span2" name="model" data-options="url:'${ctx}/hxCode/getCombobox/jx'"/></td>
					<td>费用标准<font color="red">*</font>:</td>
					<td><input name="expenseStandard" class="easyui-validatebox span2" type="text" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>管理费<font color="red">*</font>:</td>
					<td><input name="managerFee" type="text" class="easyui-validatebox span2"  /></td>
				    <td>月度奖励<font color="red">*</font>:</td>
					<td><input name="monthlyBonus" type="text" class="easyui-validatebox span2"  /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div style="text-align: right; padding: 5px">
	<a href="#" class="easyui-linkbutton" onclick="submitForm();">保存</a> <a
		href="#" class="easyui-linkbutton" onclick="continueToAdd();">继续添加</a>
	<a href="#" class="easyui-linkbutton" onclick="goBack();">返回</a>
</div>
