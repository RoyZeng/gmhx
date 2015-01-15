<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<%@ include file="../../inc/resource.inc"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
	
	function submitForm(){
		if(validate()){
		$.post(
		"${ctx}/hxFittingLocation/updateHxFittingLocation/",
		$("#form").serialize(),
		function(returnObj){
			if(returnObj.flag="success"){
				$.messager.alert('提示:','修改成功!');
				window.location.href="${ctx}/hxFittingLocation/hxFittingLocationView";
			}else{
				$.messager.alert('提示:','修改失败!');
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
	
	function goBack(){
			window.location.href="${ctx}/hxFittingLocation/hxFittingLocationView";
	}
</script>
<div class="easyui-panel" title="修改配件库位" style="width:auto">
	<div style="padding:10px 0 10px 60px">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" width="100%;">
            <td><input name="fittingId" type="hidden" value="${hxFittingLocation.fittingId}"></td>
				<tr>
					<td width="25%" align="center">机构</td>                                                                                                                                 
				    <td width="25%"><input class="easyui-combobox" name="organizationName" placeholder="请输入机构名称" class="span2" data-options="url:'${ctx}/hxCode/getCombobox/jg?value=${hxFittingLocation.organizationName}'"/></td>
				   	<td width="25%" align="center">物料类型</td>
					<td width="25%"><input name="materialType" type="text" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/wllx?value=${hxFittingLocation.materialType}'" ></td>
				</tr>
				<tr>
					<td width="25%" align="center">配件编码</td>
					<td width="25%"><input name="fittingsCode" type="text" placeholder="请输入机构名称" class="easyui-combobox" data-options="url:'${ctx}/hxCode/getCombobox/pjbm?value=${hxFittingLocation.fittingsCode}'" ></td>
				</tr>
				<tr>
					<td width="25%" align="center">库位</td>
					<td width="25%" colspan="3"><textarea  name="location"  placeholder="请输入收件人地址" rows="3" cols="50%">${hxFittingLocation.location}</textarea></td>
				</tr>
				<tr>
					<td width="25%" align="center">备注</td>
					<td width="25%" ><input name="note" type="text" placeholder="请输入备注" class="easyui-validatebox span2"  value="${hxFittingLocation.note}"></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton"  onclick="submitForm();">保存</a>
	    	<a href="#" class="easyui-linkbutton"  onclick="goBack();">返回</a>
	</div>
</div>