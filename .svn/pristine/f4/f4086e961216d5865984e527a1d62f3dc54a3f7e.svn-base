<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript"> 
 $(function() {
	
	parent.$.messager.progress('close');
});

function submitForm(){
	$.post("${ctx}/StandardSetupFree/addHxSetupeFree",$('#form').serialize(),function(returnObj){
		if(returnObj.flag="success"){
			$.messager.alert('提示:','保存成功!');
			var continueToAddFlag = $("#continueToAddFlag").val();
			if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
				window.location.href="${ctx}/StandardSetupFree/addView";
		}else{
			$.messager.alert('提示:','保存失败!');
		}}}
	);
}
	
 	function goBack(){
 		window.location.href="${ctx}/StandardSetupFree/StandardSetupFreeView";
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
					<td>机构<font color="red">*</font>:</td>
					<td><input name="freeOrganization" class="easyui-combobox"  class="span2" data-options="url:'${ctx}/hxCode/getCombobox/jg'"/></td>
				</tr>
				<tr>
				   <td>费用<font color="red">*</font>:</td>
					<td><input name="freeCost" type="text" class="easyui-validatebox span2"  /></td>
					<td>管理费<font color="red">*</font>:</td>
					<td><input name="managerCost" type="text" class="easyui-validatebox span2"  /></td>
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
