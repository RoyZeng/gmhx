<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});

	function submitForm() {
		if(validate()) {
			            $.post("${ctx}/hxFittingLocation/addHxFittingLocation",
						$('#form').serialize(),
						function(returnObj) {
							if (returnObj.flag = "success") {
								$.messager.alert('提示:', '保存成功!');
								var continueToAddFlag = $("#continueToAddFlag").val();
			 					if(continueToAddFlag=="1"){//如果是继续添加则会执行页面跳转
			 						window.location.href="${ctx}/hxFittingLocation/addView";
								}else{
									window.location.href="${ctx}/hxFittingLocation/hxFittingLocationView";
								}
							} else {
								$.messager.alert('提示:', '保存失败!');
							}
						});
		}
}
	
	function validate(){
		var flag = true;
		if($("input[name=organizationName]").val() == ""){alert("请输入机构！");return false;};
		if($("input[name=materialType]").val() == ""){alert("请选择物料类型！");return false;};
		if($("input[name=fittingsCode]").val() == ""){alert("请输入配件编码！");return false;};		
	
		return flag;
	}
	function goBack() {
		window.location.href = "${ctx}/hxFittingLocation/hxFittingLocationView";
	}

	function continueToAdd() {
		//继续添加标志位置1
		$("#continueToAddFlag").val("1");
		submitForm();
	}
	
	
</script>
<div class="easyui-panel" title="新建邮寄费用信息" style="width: auto">
	<div style="padding: 10px 0 10px 60px">
		<input type="hidden" id="continueToAddFlag" title="继续添加标志位" value="0">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
				<tr>
				    <td width="25%" align="center">机构名称</td>
					<td width="25%"><input name="organizationName" type="text" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/jg',  required:true" ></td>
					<td width="25%" align="center">物料类型</td>
					<td width="25%"><input name="materialType" type="text" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/wllx',  required:true" ></td>
				</tr>
				<tr>
					<td width="25%" align="center">配件编码</td>
					<td width="25%"><input name="fittingsCode" type="text" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/pjbm',  required:true" ></td>
				</tr>
				<tr>
					<td width="25%" align="center">库位</td>
					<td colspan="3"><textarea name="location" placeholder="请输入库位信息" rows="3" cols="50%"></textarea></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td colspan="3"><textarea name="note" placeholder="请输入备注内容" data-options="required:true"  rows="3" cols="50%"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align: center; padding: 5px">
		<a href="#" class="easyui-linkbutton" onclick="submitForm();">保存</a> 
		<a href="#" class="easyui-linkbutton" onclick="continueToAdd();">继续添加</a>
		<a href="#" class="easyui-linkbutton" onclick="goBack();">返回</a>
	</div>
</div>